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


public class CPU_3_2_2_Edit_Data_Doctor extends Activity {

	String s_doctor_id;
	String s_doctor_pw;
	String s_patient_product_num;
	
	TextView t_doctor_id;
	TextView t_patient_product_num;
	TextView t_user_name;

	TextView t_medical_num;
	TextView t_social_num;
	TextView t_product_num;
	TextView t_relative1;
	TextView t_emergency_num1;
	TextView t_relative2;
	TextView t_emergency_num2;
	TextView t_address;
	EditText t_medicine;
	EditText t_medical_history;
	EditText t_special;

	String s_medicine;
	String s_medical_history;
	String s_special;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_3_2_2_edit_data_doctor);
		Intent intent = getIntent();
		s_doctor_id = intent.getStringExtra("doctor_id");	
		s_doctor_pw = intent.getStringExtra("doctor_pw");
		s_patient_product_num = intent.getStringExtra("patient_product_num");

		t_doctor_id = (TextView)findViewById(R.id.user_id);
		t_doctor_id.setText(s_doctor_id + "님, 반갑습니다.");

		t_patient_product_num = (TextView)findViewById(R.id.patient_product_num);
		t_patient_product_num.setText(s_patient_product_num + "님의 의료정보입니다.");
		
		t_user_name = (TextView)findViewById(R.id.user_name);
		t_medical_num = (TextView)findViewById(R.id.call_medical_num);
		t_social_num = (TextView)findViewById(R.id.call_social_num);
		t_product_num = (TextView)findViewById(R.id.call_product_num);
		t_relative1 = (TextView)findViewById(R.id.call_relative1);
		t_emergency_num1 = (TextView)findViewById(R.id.call_emergency_num1);
		t_relative2 = (TextView)findViewById(R.id.call_relative2);
		t_emergency_num2 = (TextView)findViewById(R.id.call_emergency_num2);
		t_address = (TextView)findViewById(R.id.call_address);
		t_medicine = (EditText)findViewById(R.id.call_medicine);
		t_medical_history = (EditText)findViewById(R.id.call_medical_history);
		t_special = (EditText)findViewById(R.id.call_special);

		String strUrl = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + s_patient_product_num;
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
			String tag_start[] = {"name=","medical_num=","social_num=","product_num=","relative1=","emergency_num1=","relative2=","emergency_num2=","address=","medicine=","medical_history=","special="};
			String tag_end[] = {"=name","=medical_num","=social_num","=product_num","=relative1","=emergency_num1","=relative2","=emergency_num2","=address","=medicine","=medical_history","=special"};

			for(i=0; i<12; i++) {

				pt_start= result.indexOf(tag_start[i]);
				if(pt_start != -1) {
					pt_end = result.indexOf(tag_end[i]);

					if(pt_end != -1) {
						word = result.substring(pt_start + tag_start[i].length(), pt_end);
						if(i==0) t_user_name.setText(word);
						if(i==1) t_medical_num.setText(word);
						if(i==2) t_social_num.setText(word);
						if(i==3) t_product_num.setText(word);
						if(i==4) t_relative1.setText(word);
						if(i==5) t_emergency_num1.setText(word);
						if(i==6) t_relative2.setText(word);
						if(i==7) t_emergency_num2.setText(word);
						if(i==8) t_address.setText(word);
						if(i==9) t_medicine.setText(word);
						if(i==10) t_medical_history.setText(word);
						if(i==11) t_special.setText(word);
					} else
						t_special.setText("데이터가 없습니다!");    			
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

		s_patient_product_num = t_product_num.getText().toString();
		s_medicine = t_medicine.getText().toString();
		s_medical_history = t_medical_history.getText().toString();
		s_special = t_special.getText().toString();


		String strUrl = "http://220.67.113.124/CPU/mobile/edit_patient.php?product_num="
							 +URLEncoder.encode(s_patient_product_num,"UTF-8")
				+"&medicine="+URLEncoder.encode(s_medicine,"UTF-8")
				+"&medical_history="+URLEncoder.encode(s_medical_history,"UTF-8")
				+"&special="+URLEncoder.encode(s_special,"UTF-8")
				+"&mode="+URLEncoder.encode("modify","UTF-8");

		String url = new String(strUrl.getBytes(),"UTF-8");

		new DownloadWebpageTask().execute(url);

		Intent data = new Intent(this, CPU_3_2_1_Data.class);
		data.putExtra("doctor_id", s_doctor_id);
		data.putExtra("doctor_pw", s_doctor_pw);
		data.putExtra("patient_product_num", s_patient_product_num);
		startActivity(data);
		this.onDestroy();
	}

	public void introduce_maker(View v){		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent introduce_maker = new Intent(this, CPU_3_1_1_Maker.class);
		introduce_maker.putExtra("doctor_id", s_doctor_id);
		introduce_maker.putExtra("doctor_pw", s_doctor_pw);
		startActivity(introduce_maker);
		this.onDestroy();
	}
	public void introduce_hello(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent introduce_hello = new Intent(this, CPU_3_1_2_Hello.class);
		introduce_hello.putExtra("doctor_id", s_doctor_id);
		introduce_hello.putExtra("doctor_pw", s_doctor_pw);
		startActivity(introduce_hello);
		this.onDestroy();
	}
	public void introduce_product2(View v){		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent introduce_product2 = new Intent(this, CPU_3_1_3_IntroduceProduct.class);
		introduce_product2.putExtra("doctor_id", s_doctor_id);
		introduce_product2.putExtra("doctor_pw", s_doctor_pw);
		startActivity(introduce_product2);
		this.onDestroy();
	}
	public void introduce_howtouse(View v){		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent introduce_howtouse = new Intent(this, CPU_3_1_4_HowToUse.class);
		introduce_howtouse.putExtra("doctor_id", s_doctor_id);
		introduce_howtouse.putExtra("doctor_pw", s_doctor_pw);
		startActivity(introduce_howtouse);
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
	

	public void mypage_medicaldata(View v){		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent mypage_medicaldata = new Intent(this, CPU_3_2_1_Data.class);
		mypage_medicaldata.putExtra("doctor_id", s_doctor_id);
		mypage_medicaldata.putExtra("doctor_pw", s_doctor_pw);
		mypage_medicaldata.putExtra("patient_product_num", s_patient_product_num);
		startActivity(mypage_medicaldata);
		this.onDestroy();
	}
	public void mypage_graph(View v){		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent mypage_graph = new Intent(this, CPU_3_2_3_Graph.class);
		mypage_graph.putExtra("doctor_id", s_doctor_id);
		mypage_graph.putExtra("doctor_pw", s_doctor_pw);
		mypage_graph.putExtra("patient_product_num", s_patient_product_num);
		startActivity(mypage_graph);
		this.onDestroy();
	}
	public void mypage_photo(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent mypage_photo = new Intent(this, CPU_3_2_4_Photo.class);
		mypage_photo.putExtra("doctor_id", s_doctor_id);
		mypage_photo.putExtra("doctor_pw", s_doctor_pw);
		mypage_photo.putExtra("patient_product_num", s_patient_product_num);
		startActivity(mypage_photo);
		this.onDestroy();
	}
	public void mypage_alram(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent mypage_alram = new Intent(this, CPU_3_2_5_Alram.class);
		mypage_alram.putExtra("doctor_id", s_doctor_id);
		mypage_alram.putExtra("doctor_pw", s_doctor_pw);
		mypage_alram.putExtra("patient_product_num", s_patient_product_num);
		startActivity(mypage_alram);
		this.onDestroy();
	}

}
