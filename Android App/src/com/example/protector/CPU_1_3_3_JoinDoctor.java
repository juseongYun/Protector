package com.example.protector;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class CPU_1_3_3_JoinDoctor extends Activity {

	EditText t_doctor_id;
	EditText t_doctor_pw;
	EditText t_user_name;
	EditText t_license_num;
	EditText t_phone_num;

	String s_doctor_id;
	String s_doctor_pw;
	String s_user_name;
	String s_license_num;
	String s_phone_num;

	String encoding_pw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_1_3_3_joindoctor);

	}

	private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {
			try{
				return (String)downloadUrl((String)urls[0]);
			} catch (IOException e) {
				return "다운로드 실패";
			}
		}


		private String downloadUrl(String myurl) throws IOException {
			HttpURLConnection conn = null;
			try {
				URL url = new URL(myurl);
				conn = (HttpURLConnection)url.openConnection();
				BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
				BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
				String line = null;
				String page = "";
				while((line = bufreader.readLine()) != null) {
					page += line;
				}

				return page;
			} finally {
				conn.disconnect();
			}
		}
	}


	protected void onDestroy(){		
		super.onDestroy();
		this.finish();
	}	

	public void main(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent main = new Intent(this, ActivityMain.class);
		startActivity(main);
		this.onDestroy();
	}

	public void login(View v) throws Exception{				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);

		t_doctor_id = (EditText)findViewById(R.id.edit_user_id);
		t_doctor_pw = (EditText)findViewById(R.id.edit_user_pw);
		t_user_name = (EditText)findViewById(R.id.edit_user_name);
		t_license_num = (EditText)findViewById(R.id.edit_license_num);
		t_phone_num = (EditText)findViewById(R.id.edit_phone_num);


		s_doctor_id = t_doctor_id.getText().toString();
		s_doctor_pw = t_doctor_pw.getText().toString();
		s_user_name = t_user_name.getText().toString();
		s_license_num = t_license_num.getText().toString();
		s_phone_num = t_phone_num.getText().toString();

		try {
			encoding_pw = toSHA1(s_doctor_pw);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		String strUrl = "http://220.67.113.124/CPU/mobile/insert_doctor.php?userid="
						   +URLEncoder.encode(s_doctor_id,"UTF-8")
				+"&userpw="+URLEncoder.encode(encoding_pw,"UTF-8")
				+"&mode="+URLEncoder.encode(" ", "UTF-8")
				+"&job="+URLEncoder.encode("의사","UTF-8")
				+"&name="+URLEncoder.encode(s_user_name,"UTF-8")
				+"&license_num=+"+URLEncoder.encode(s_license_num,"UTF-8")
				+"&phone_num="+URLEncoder.encode(s_phone_num,"UTF-8");


		new DownloadWebpageTask().execute(strUrl);			

		Intent login = new Intent(this, CPU_1_2_2_LoginDoctor.class);		
		startActivity(login);
		this.onDestroy();
	}
	//암호화
	public static String toSHA1(String s) throws UnsupportedEncodingException {
		try {
			// Create MD5 Hash
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(s.getBytes("UTF-8"));
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++)
				hexString.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}



}

