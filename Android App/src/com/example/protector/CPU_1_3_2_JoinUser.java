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

public class CPU_1_3_2_JoinUser extends Activity {

	EditText t_user_id;
	EditText t_user_pw;
	EditText t_user_name;
	EditText t_medical_num;
	EditText t_social_num1;
	EditText t_social_num2;
	EditText t_product_num;
	EditText t_relative1;
	EditText t_emergency_num1;
	EditText t_relative2;
	EditText t_emergency_num2;
	EditText t_address;
	EditText t_medicine;
	EditText t_medical_history;
	EditText t_special;

	String s_user_id;
	String s_user_pw;
	String s_user_name;
	String s_medical_num;
	String s_social_num;
	String s_social_num1;
	String s_social_num2;
	String s_product_num;
	String s_relative1;
	String s_emergency_num1;
	String s_relative2;
	String s_emergency_num2;
	String s_address;
	String s_medicine;
	String s_medical_history;
	String s_special;

	String encoding_pw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_1_3_2_joinuser);	

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

		t_user_id = (EditText)findViewById(R.id.edit_user_id);
		t_user_pw = (EditText)findViewById(R.id.edit_user_pw);
		t_user_name = (EditText)findViewById(R.id.edit_user_name);
		t_medical_num = (EditText)findViewById(R.id.edit_medical_num);
		t_social_num1 = (EditText)findViewById(R.id.edit_social_num1);
		t_social_num2 = (EditText)findViewById(R.id.edit_social_num2);
		t_product_num = (EditText)findViewById(R.id.edit_product_num);
		t_relative1 = (EditText)findViewById(R.id.edit_relative1);
		t_emergency_num1 = (EditText)findViewById(R.id.edit_emergency_num1);
		t_relative2 = (EditText)findViewById(R.id.edit_relative2);
		t_emergency_num2 = (EditText)findViewById(R.id.edit_emergency_num2);
		t_address = (EditText)findViewById(R.id.edit_address);
		t_medicine = (EditText)findViewById(R.id.edit_medicine);
		t_medical_history = (EditText)findViewById(R.id.edit_medical_history);
		t_special = (EditText)findViewById(R.id.edit_special);


		s_user_id = t_user_id.getText().toString();
		s_user_pw = t_user_pw.getText().toString();
		s_user_name = t_user_name.getText().toString();
		s_medical_num = t_medical_num.getText().toString();
		s_social_num = t_social_num1.getText().toString() + "-" + t_social_num2.getText().toString();
		s_product_num = t_product_num.getText().toString();
		s_relative1 = t_relative1.getText().toString();
		s_emergency_num1 = t_emergency_num1.getText().toString();
		s_relative2 = t_relative2.getText().toString();
		s_emergency_num2 = t_emergency_num2.getText().toString();
		s_address = t_address.getText().toString();
		s_medicine = t_medicine.getText().toString();
		s_medical_history = t_medical_history.getText().toString();
		s_special = t_special.getText().toString();


		try {
			encoding_pw = toSHA1(s_user_pw);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}



		String strUrl = "http://220.67.113.124/CPU/mobile/insert.php?userid="
				+URLEncoder.encode(s_user_id,"UTF-8")
				+"&userpw="+URLEncoder.encode(encoding_pw,"UTF-8")
				+"&mode="+URLEncoder.encode(" ", "UTF-8")
				+"&name="+URLEncoder.encode(s_user_name,"UTF-8")
				+"&medical_num=+"+URLEncoder.encode(s_medical_num,"UTF-8")
				+"&social_num="+URLEncoder.encode(s_social_num,"UTF-8")
				+"&product_num="+URLEncoder.encode(s_product_num,"UTF-8")
				+"&relative1="+URLEncoder.encode(s_relative1,"UTF-8")
				+"&emergency_num1="+URLEncoder.encode(s_emergency_num1,"UTF-8")
				+"&relative2="+URLEncoder.encode(s_relative2,"UTF-8")
				+"&emergency_num2="+URLEncoder.encode(s_emergency_num2,"UTF-8")
				+"&address="+URLEncoder.encode(s_address,"UTF-8")
				+"&medicine="+URLEncoder.encode(s_medicine,"UTF-8")
				+"&medical_history="+URLEncoder.encode(s_medical_history,"UTF-8")
				+"&special="+URLEncoder.encode(s_special,"UTF-8");


		new DownloadWebpageTask().execute(strUrl);			

		Intent login = new Intent(this, CPU_1_2_1_LoginUser.class);	
		startActivity(login);
		this.onDestroy();

	}



	public void introduce_product(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent introduce_product = new Intent(this, CPU_2_1_1_Maker.class);
		introduce_product.putExtra("user_id", s_user_id);
		introduce_product.putExtra("user_pw", encoding_pw);
		startActivity(introduce_product);
		this.onDestroy();
	}
	public void mypage(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent mypage = new Intent(this, CPU_2_2_1_Edit_Data.class);
		mypage.putExtra("user_id", s_user_id);
		mypage.putExtra("user_pw", encoding_pw);
		startActivity(mypage);
		this.onDestroy();
	}
	public void service(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent service = new Intent(this, CPU_2_3_1_FAQ.class);
		service.putExtra("user_id", s_user_id);
		service.putExtra("user_pw", encoding_pw);
		startActivity(service);
		this.onDestroy();
	}

	public void mypage_medicaldata(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);
		Intent mypage_medicaldata = new Intent(this, CPU_2_2_1_Data.class);
		mypage_medicaldata.putExtra("user_id", s_user_id);
		mypage_medicaldata.putExtra("user_pw", encoding_pw);
		startActivity(mypage_medicaldata);
		this.onDestroy();
	}
	public void mypage_graph(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);
		Intent mypage_graph = new Intent(this, CPU_2_2_2_Graph.class);
		mypage_graph.putExtra("user_id", s_user_id);
		mypage_graph.putExtra("user_pw", encoding_pw);
		startActivity(mypage_graph);
		this.onDestroy();
	}
	public void mypage_photo(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent mypage_photo = new Intent(this, CPU_2_2_3_Photo.class);
		mypage_photo.putExtra("user_id", s_user_id);
		mypage_photo.putExtra("user_pw", encoding_pw);
		startActivity(mypage_photo);
		this.onDestroy();
	}
	public void mypage_alram(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent mypage_alram = new Intent(this, CPU_2_2_4_Alram.class);
		mypage_alram.putExtra("user_id", s_user_id);
		mypage_alram.putExtra("user_pw", encoding_pw);
		startActivity(mypage_alram);
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
