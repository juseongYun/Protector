package com.example.protector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
public class CPU_3_2_1_Data_Personal extends Activity {

	private PopupWindow mPopupWindow;
	String s_doctor_id;
	String s_doctor_pw;
	TextView t_doctor_id;
	Handler handler = new Handler(); 
	TextView t_user_name;
	TextView t_license_num;
	TextView t_phone_num;
	String pageContents = "";
	StringBuilder contents = new StringBuilder();
	private Socket socket;
	BufferedReader socket_in;
	PrintWriter socket_out;
	EditText input;
	Button button;
	TextView output;
	String data;
	String datat;
	Vibrator vide;
	String strUrl;
	public scworker gg;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_3_2_1_data_personal);	
		Intent intent = getIntent();
		s_doctor_id = intent.getStringExtra("doctor_id");	
		s_doctor_pw = intent.getStringExtra("doctor_pw");	

		t_doctor_id = (TextView)findViewById(R.id.user_id);
		t_doctor_id.setText(s_doctor_id + "님, 반갑습니다.");
		
		strUrl = "http://220.67.113.124/CPU/mobile/doctor.php?id="+s_doctor_id+"&pass="+s_doctor_pw;
		//new DownloadWebpageTask().execute(strUrl);

		t_user_name = (TextView)findViewById(R.id.user_name);
		t_license_num = (TextView)findViewById(R.id.call_license_num);
		t_phone_num = (TextView)findViewById(R.id.call_phone_num);

		t_doctor_id = (TextView)findViewById(R.id.user_id);
		vide = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);    
		parse(strUrl);
		gg = new scworker();
		gg.start();

	}

	public void parse(String urlPath){
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads()
				.detectDiskWrites().detectNetwork().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);
		try{

			URL url = new URL(urlPath);
			URLConnection con = (URLConnection)url.openConnection();
			InputStreamReader reader = new InputStreamReader (con.getInputStream(), "utf-8");

			BufferedReader buff = new BufferedReader(reader);
			contents = new StringBuilder();
			while((pageContents = buff.readLine())!=null){
				//System.out.println(pageContents);             
				contents.append(pageContents);
				contents.append("\r\n");
			}
			buff.close();
			String result=contents.toString();
			String word="";
			int pt_start=-1;
			int pt_end=-1;
			int i;
			String tag_start[] = {"name=","license_num=","phone_num="};
			String tag_end[] = {"=name","=license_num","=phone_num"};

			for(i=0; i<3; i++) {

				pt_start= result.indexOf(tag_start[i]);
				if(pt_start != -1) {
					pt_end = result.indexOf(tag_end[i]);

					if(pt_end != -1) {
						word = result.substring(pt_start + tag_start[i].length(), pt_end);
						if(i==0) t_user_name.setText(word);
						else if(i==1) t_license_num.setText(word);
						else if(i==2) t_phone_num.setText(word);
					} else
						t_license_num.setText(" ");    			
				} 
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}



	class scworker extends Thread {  

		public scworker(){


		}
		public void run() {
			try {
				socket = new Socket("220.67.113.64", 9000); 
				//data = t_product_num.getText().toString();	   			
				socket_out = new PrintWriter(socket.getOutputStream(), true);
				socket_out.println(data);
				socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				//Toast.makeText(getApplicationContext(),"asda", Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			while(true) {
				try {       			        			

					datat = socket_in.readLine();
					handler.post(new Runnable() {
						public void run() {
							//output.setText(datat);
							Toast.makeText(getApplicationContext(),"\n★PROTECTOR★\n응급상황 입니다!!\n",  Toast.LENGTH_SHORT).show();
							long[] pattern = { 20, 400, 100, 400, 100, 400, 100, 400}; 
							vide.vibrate(pattern, -1);
							Intent intent;
							intent = new Intent(CPU_3_2_1_Data_Personal.this, MyAlert.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(intent);

						}
					});
				}
				catch (Exception e) {  			
				}}
		}
	};

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
	public void edit_data(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent edit_data = new Intent(this, CPU_3_2_2_Edit_Data_Personal.class);
		edit_data.putExtra("doctor_id", s_doctor_id);
		edit_data.putExtra("doctor_pw", s_doctor_pw);
		startActivity(edit_data);
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
