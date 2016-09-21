package com.example.protector;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CPU_1_1_2_ShowData extends Activity  {

	String s_product_num;
	TextView t_product_num;

	TextView t_user_name;
	TextView t_medical_num;
	TextView t_social_num;
	TextView t_emergency_num1;
	TextView t_emergency_num2;
	TextView t_address;
	TextView t_medicine;
	TextView t_medical_history;
	TextView t_special;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_1_1_2_showdata);	
		Intent intent = getIntent();
		s_product_num = intent.getStringExtra("product_num");
		t_product_num = (TextView)findViewById(R.id.product_num);

		t_user_name = (TextView)findViewById(R.id.user_name);
		t_medical_num = (TextView)findViewById(R.id.call_medical_num);
		t_social_num = (TextView)findViewById(R.id.call_social_num);
		t_emergency_num1 = (TextView)findViewById(R.id.call_emergency_num1);
		t_emergency_num2 = (TextView)findViewById(R.id.call_emergency_num2);
		t_address = (TextView)findViewById(R.id.call_address);
		t_medicine = (TextView)findViewById(R.id.call_medicine);
		t_medical_history = (TextView)findViewById(R.id.call_medical_history);
		t_special = (TextView)findViewById(R.id.call_special);

		String strUrl = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + s_product_num;
		new DownloadWebpageTask().execute(strUrl);
	}
	private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {
			try{
				return (String)downloadUrl((String)urls[0]);
			} catch (IOException e) {
				return "error";
			}
		}

		protected void onPostExecute(String result) {
			String word="";
			int pt_start=-1;
			int pt_end=-1;
			int i;
			String tag_start[] = {"name=","medical_num=","social_num=","product_num=","re1=","re2=","address=","medicine=","medical_history=","special="};
			String tag_end[] = {"=name","=medical_num","=social_num","=product_num","=re1","=re2","=address","=medicine","=medical_history","=special"};

			for(i=0; i<10; i++) {

				pt_start= result.indexOf(tag_start[i]);
				if(pt_start != -1) {
					pt_end = result.indexOf(tag_end[i]);

					if(pt_end != -1) {
						word = result.substring(pt_start + tag_start[i].length(), pt_end);
						if(i==0) t_user_name.setText(word);
						if(i==1) t_medical_num.setText(word);
						if(i==2) t_social_num.setText(word);
						if(i==3) t_product_num.setText(word+"´Ô, ¹Ý°©½À´Ï´Ù.");
						if(i==4) t_emergency_num1.setText(word);
						if(i==5) t_emergency_num2.setText(word);
						if(i==6) t_address.setText(word);
						if(i==7) t_medicine.setText(word);
						if(i==8) t_medical_history.setText(word);
						if(i==9) t_special.setText(word);

					} 	 
				}

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

	public void show_graph(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent show_graph = new Intent(this, CPU_1_1_3_ShowGraph.class);
		show_graph.putExtra("product_num", s_product_num);
		startActivity(show_graph);
		this.onDestroy();
	}
	public void show_photo(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent show_photo = new Intent(this, CPU_1_1_4_ShowPhoto.class);
		show_photo.putExtra("product_num", s_product_num);
		startActivity(show_photo);
		this.onDestroy();
	}
	public void show_alram(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent show_alram = new Intent(this, CPU_1_1_5_ShowAlram.class);
		show_alram.putExtra("product_num", s_product_num);
		startActivity(show_alram);
		this.onDestroy();
	}

	public void login(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent login = new Intent(this, CPU_1_2_1_LoginUser.class);
		startActivity(login);
		this.onDestroy();
	}


}
