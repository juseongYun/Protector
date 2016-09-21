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


public class CPU_2_2_1_Edit_Data extends Activity {

	String s_user_id;
	String s_user_pw;
	TextView t_user_id;
	TextView t_user_name;

	EditText t_medical_num;
	EditText t_social_num;
	EditText t_product_num;
	EditText t_relative1;
	EditText t_emergency_num1;
	EditText t_relative2;
	EditText t_emergency_num2;
	EditText t_address;
	EditText t_medicine;
	EditText t_medical_history;
	EditText t_special;

	String s_user_name;
	String s_medical_num;
	String s_social_num;
	String s_product_num;
	String s_relative1;
	String s_emergency_num1;
	String s_relative2;
	String s_emergency_num2;
	String s_address;
	String s_medicine;
	String s_medical_history;
	String s_special;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_2_2_1_edit_data);	
		Intent intent = getIntent();
		s_user_id = intent.getStringExtra("user_id");	
		s_user_pw = intent.getStringExtra("user_pw");

		t_user_name = (TextView)findViewById(R.id.user_name);
		t_medical_num = (EditText)findViewById(R.id.call_medical_num);
		t_social_num = (EditText)findViewById(R.id.call_social_num);
		t_product_num = (EditText)findViewById(R.id.call_product_num);
		t_relative1 = (EditText)findViewById(R.id.call_relative1);
		t_emergency_num1 = (EditText)findViewById(R.id.call_emergency_num1);
		t_relative2 = (EditText)findViewById(R.id.call_relative2);
		t_emergency_num2 = (EditText)findViewById(R.id.call_emergency_num2);
		t_address = (EditText)findViewById(R.id.call_address);
		t_medicine = (EditText)findViewById(R.id.call_medicine);
		t_medical_history = (EditText)findViewById(R.id.call_medical_history);
		t_special = (EditText)findViewById(R.id.call_special);

		t_user_id = (TextView)findViewById(R.id.user_id);

		String strUrl = "http://220.67.113.124/CPU/mobile/myprofile2.php?id="+s_user_id+"&pass="+s_user_pw;
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
			String tag_start[] = {"id=","name=","medical_num=","social_num=","product_num=","relative1=","emergency_num1=","relative2=","emergency_num2=","address=","medicine=","medical_history=","special="};
			String tag_end[] = {"=id","=name","=medical_num","=social_num","=product_num","=relative1","=emergency_num1","=relative2","=emergency_num2","=address","=medicine","=medical_history","=special"};

			for(i=0; i<13; i++) {

				pt_start= result.indexOf(tag_start[i]);
				if(pt_start != -1) {
					pt_end = result.indexOf(tag_end[i]);

					if(pt_end != -1) {
						word = result.substring(pt_start + tag_start[i].length(), pt_end);
						if(i==0) t_user_id.setText(word+"님, 반갑습니다.");
						if(i==1) t_user_name.setText(word);
						if(i==2) t_medical_num.setText(word);
						if(i==3) t_social_num.setText(word);
						if(i==4) t_product_num.setText(word);
						if(i==5) t_relative1.setText(word);
						if(i==6) t_emergency_num1.setText(word);
						if(i==7) t_relative2.setText(word);
						if(i==8) t_emergency_num2.setText(word);
						if(i==9) t_address.setText(word);
						if(i==10) t_medicine.setText(word);
						if(i==11) t_medical_history.setText(word);
						if(i==12) t_special.setText(word);
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

		s_user_name = t_user_name.getText().toString();
		s_medical_num = t_medical_num.getText().toString();
		s_social_num = t_social_num.getText().toString();
		s_product_num = t_product_num.getText().toString();
		s_relative1 = t_relative1.getText().toString();
		s_emergency_num1 = t_emergency_num1.getText().toString();
		s_relative2 = t_relative2.getText().toString();
		s_emergency_num2 = t_emergency_num2.getText().toString();
		s_address = t_address.getText().toString();
		s_medicine = t_medicine.getText().toString();
		s_medical_history = t_medical_history.getText().toString();
		s_special = t_special.getText().toString();


		String strUrl = "http://220.67.113.124/CPU/mobile/insert.php?userid="
						 +URLEncoder.encode(s_user_id,"UTF-8")
				+"&userpw="+URLEncoder.encode(s_user_pw,"UTF-8")
				+"&mode="+URLEncoder.encode("modify","UTF-8")
				+"&name="+URLEncoder.encode(s_user_name,"UTF-8")
				+"&medical_num="+URLEncoder.encode(s_medical_num,"UTF-8")
				+"&social_num="+URLEncoder.encode(s_social_num,"UTF-8")
				+"&product_num="+URLEncoder.encode(s_product_num,"UTF-8")
				+"&relative1="+URLEncoder.encode(s_relative1,"UTF-8")
				+"&emergency_num1="+URLEncoder.encode(s_emergency_num1,"UTF-8")
				+"&relative2="+URLEncoder.encode(s_relative1,"UTF-8")
				+"&emergency_num2="+URLEncoder.encode(s_emergency_num2,"UTF-8")
				+"&address="+URLEncoder.encode(s_address,"UTF-8")
				+"&medicine="+URLEncoder.encode(s_medicine,"UTF-8")
				+"&medical_history="+URLEncoder.encode(s_medical_history,"UTF-8")
				+"&special="+URLEncoder.encode(s_special,"UTF-8");

		String url = new String(strUrl.getBytes(),"UTF-8");

		new DownloadWebpageTask().execute(url);

		Intent data = new Intent(this, CPU_2_2_1_Data.class);
		data.putExtra("user_id", s_user_id);
		data.putExtra("user_pw", s_user_pw);
		startActivity(data);
		this.onDestroy();
	}

	public void introduce_product(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);
		Intent introduce_product = new Intent(this, CPU_2_1_1_Maker.class);
		introduce_product.putExtra("user_id", s_user_id);
		introduce_product.putExtra("user_pw", s_user_pw);
		startActivity(introduce_product);
		this.onDestroy();
	}
	public void mypage(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);
		Intent mypage = new Intent(this, CPU_2_2_1_Edit_Data.class);
		mypage.putExtra("user_id", s_user_id);
		mypage.putExtra("user_pw", s_user_pw);
		startActivity(mypage);
		this.onDestroy();
	}
	public void service(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);
		Intent service = new Intent(this, CPU_2_3_1_FAQ.class);
		service.putExtra("user_id", s_user_id);
		service.putExtra("user_pw", s_user_pw);
		startActivity(service);
		this.onDestroy();
	}

	public void mypage_medicaldata(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent mypage_medicaldata = new Intent(this, CPU_2_2_1_Data.class);
		mypage_medicaldata.putExtra("user_id", s_user_id);
		mypage_medicaldata.putExtra("user_pw", s_user_pw);
		startActivity(mypage_medicaldata);
		this.onDestroy();
	}
	public void mypage_graph(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);
		Intent mypage_graph = new Intent(this, CPU_2_2_2_Graph.class);
		mypage_graph.putExtra("user_id", s_user_id);
		mypage_graph.putExtra("user_pw", s_user_pw);
		startActivity(mypage_graph);
		this.onDestroy();
	}
	public void mypage_photo(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent mypage_photo = new Intent(this, CPU_2_2_3_Photo.class);
		mypage_photo.putExtra("user_id", s_user_id);
		mypage_photo.putExtra("user_pw", s_user_pw);
		startActivity(mypage_photo);
		this.onDestroy();
	}
	public void mypage_alram(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent mypage_alram = new Intent(this, CPU_2_2_4_Alram.class);
		mypage_alram.putExtra("user_id", s_user_id);
		mypage_alram.putExtra("user_pw", s_user_pw);
		startActivity(mypage_alram);
		this.onDestroy();
	}

}
