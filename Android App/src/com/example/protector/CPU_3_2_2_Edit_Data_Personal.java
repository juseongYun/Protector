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


public class CPU_3_2_2_Edit_Data_Personal extends Activity {

	String s_doctor_id;
	String s_doctor_pw;
	
	TextView t_doctor_name;
	TextView t_doctor_id;

	EditText t_phone_num;
	EditText t_license_num;

	String s_doctor_name;
	String s_phone_num;
	String s_license_num;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_3_2_2_edit_data_personal);	
		Intent intent = getIntent();
		s_doctor_id = intent.getStringExtra("doctor_id");	
		s_doctor_pw = intent.getStringExtra("doctor_pw");

		t_doctor_id = (TextView)findViewById(R.id.user_id);
		t_doctor_id.setText(s_doctor_id + "님, 반갑습니다.");

		
		t_doctor_name = (TextView)findViewById(R.id.user_name);
		t_phone_num = (EditText)findViewById(R.id.edit_phone_num);
		t_license_num = (EditText)findViewById(R.id.edit_license_num);
		

		String strUrl = "http://220.67.113.124/CPU/mobile/doctor.php?id="+s_doctor_id+"&pass="+s_doctor_pw;
		new DownloadWebpageTask().execute(strUrl);
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

		public void onPostExecute(String result) {
			String word="";
			int pt_start=-1;
			int pt_end=-1;
			int i;
			String tag_start[] = {"name=","phone_num=","license_num="};
			String tag_end[] = {"=name","=phone_num","=license_num"};

			for(i=0; i<3; i++) {

				pt_start= result.indexOf(tag_start[i]);
				if(pt_start != -1) {
					pt_end = result.indexOf(tag_end[i]);

					if(pt_end != -1) {
						word = result.substring(pt_start + tag_start[i].length(), pt_end);
						if(i==0) t_doctor_name.setText(word);
						if(i==1) t_phone_num.setText(word);
						if(i==2) t_license_num.setText(word);
						
					} else
						t_phone_num.setText("데이터가 없습니다!");    			
				} 
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

	public void edit_save(View v) throws Exception{	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			

		s_doctor_name = t_doctor_name.getText().toString();
		s_phone_num = t_phone_num.getText().toString();
		s_license_num = t_license_num.getText().toString();
		
		String strUrl = "http://220.67.113.124/CPU/mobile/insert_doctor.php?userid="
						 +URLEncoder.encode(s_doctor_id,"UTF-8")
				+"&userpw="+URLEncoder.encode(s_doctor_pw,"UTF-8")
				+"&mode="+URLEncoder.encode("modify","UTF-8")
				+"&name="+URLEncoder.encode(s_doctor_name,"UTF-8")				
				+"&license_num="+URLEncoder.encode(s_license_num,"UTF-8")
				+"&phone_num="+URLEncoder.encode(s_phone_num,"UTF-8");

		String url = new String(strUrl.getBytes(),"UTF-8");

		new DownloadWebpageTask().execute(url);

		Intent data_personal = new Intent(this, CPU_3_2_1_Data_Personal.class);
		data_personal.putExtra("doctor_id", s_doctor_id);
		data_personal.putExtra("doctor_pw", s_doctor_pw);
		startActivity(data_personal);
		this.onDestroy();
	}

	public void introduce_product(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent introduce_product = new Intent(this, CPU_3_1_1_Maker.class);
		introduce_product.putExtra("doctor_id", s_doctor_id);
		introduce_product.putExtra("doctor_pw", s_doctor_pw);
		startActivity(introduce_product);
		this.onDestroy();
	}
	public void mypage(View v){		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent mypage = new Intent(this, CPU_3_2_0_Patient_List.class);
		mypage.putExtra("doctor_id", s_doctor_id);
		mypage.putExtra("doctor_pw", s_doctor_pw);
		startActivity(mypage);
		this.onDestroy();
	}
	public void service(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent service = new Intent(this, CPU_3_3_1_FAQ.class);
		service.putExtra("doctor_id", s_doctor_id);
		service.putExtra("doctor_pw", s_doctor_pw);
		startActivity(service);
		this.onDestroy();
	}

	public void mypage_patient(View v){		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent mypage_patient = new Intent(this, CPU_3_2_0_Patient_List.class);
		mypage_patient.putExtra("doctor_id", s_doctor_id);
		mypage_patient.putExtra("doctor_pw", s_doctor_pw);
		startActivity(mypage_patient);
		this.onDestroy();
	}
	
	public void mypage_doctor(View v){		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent mypage_doctor = new Intent(this, CPU_3_2_1_Data_Personal.class);
		mypage_doctor.putExtra("doctor_id", s_doctor_id);
		mypage_doctor.putExtra("doctor_pw", s_doctor_pw);
		startActivity(mypage_doctor);
		this.onDestroy();
	}
	

}
