package com.example.protector;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class CPU_3_2_0_Add_Patient extends Activity {

	String s_doctor_id;
	String s_doctor_pw;
	TextView t_doctor_id;

	String d_encoding_pw;


	TextView t_patient1_name;
	TextView t_patient1_birth;
	TextView t_patient1_product_num;

	TextView t_patient2_name;
	TextView t_patient2_birth;
	TextView t_patient2_product_num;

	TextView t_patient3_name;
	TextView t_patient3_birth;
	TextView t_patient3_product_num;

	TextView t_patient4_name;
	TextView t_patient4_birth;
	TextView t_patient4_product_num;

	TextView t_patient5_name;
	TextView t_patient5_birth;
	TextView t_patient5_product_num;

	String urlPath;
	String pageContents = "";
	StringBuilder contents = new StringBuilder();

	String s_patient_product_num;
	EditText t_patient_product_num;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_3_2_0_add_patient);

		Intent intent = getIntent();
		s_doctor_id = intent.getStringExtra("doctor_id");	
		s_doctor_pw = intent.getStringExtra("doctor_pw");

		t_doctor_id = (TextView)findViewById(R.id.user_id);
		t_doctor_id.setText(s_doctor_id + "님, 반갑습니다.");

		t_patient_product_num = (EditText)findViewById(R.id.t_patient_product_num);


	}

	public class DownloadWebpageTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {
			try{
				return (String)downloadUrl((String)urls[0]);
			} catch (IOException e) {
				return "다운로드 실패";
			}
		}

		public String downloadUrl(String myurl) throws IOException {
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



	public void add_patient(View v)throws Exception{	

		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	

		s_patient_product_num = t_patient_product_num.getText().toString();

		if(s_patient_product_num.equals("")){
			Toast toast = Toast.makeText(this, "제품번호를 입력해주세요~", Toast.LENGTH_SHORT);
			//원하는 텍스트 메세지가 뜨도록 Toast 변수를 선언하고 문자열을 지정한다.
			toast.show();
			//toast가 보여지게 한다.
		}
		else{		

			String strUrl = "http://220.67.113.124/CPU/mobile/insert_patient.php?userid="
							 +URLEncoder.encode(s_doctor_id,"UTF-8")
					+"&mode="+URLEncoder.encode("2","UTF-8")					
					+"&userpass="+URLEncoder.encode(s_doctor_pw,"UTF-8")
					+"&product_num="+URLEncoder.encode(s_patient_product_num,"UTF-8");

			String url = new String(strUrl.getBytes(),"UTF-8");

			new DownloadWebpageTask().execute(url);

			Intent mypage = new Intent(this, CPU_3_2_0_Patient_List.class);
			mypage.putExtra("doctor_id", s_doctor_id);
			mypage.putExtra("doctor_pw", s_doctor_pw);
			startActivity(mypage);
			this.onDestroy();
		}

	}


	protected void onDestroy(){		
		super.onDestroy();
		this.finish();
	}


}





