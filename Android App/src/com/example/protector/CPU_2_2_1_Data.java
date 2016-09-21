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
public class CPU_2_2_1_Data extends Activity {

	private PopupWindow mPopupWindow;
	String s_user_id;
	String s_user_pw;
	TextView t_user_id;
	Handler handler = new Handler(); 
	TextView t_user_name;
	TextView t_medical_num;
	TextView t_social_num;
	TextView t_product_num;
	TextView t_relative1;
	TextView t_emergency_num1;
	TextView t_relative2;
	TextView t_emergency_num2;
	TextView t_address;
	TextView t_medicine;
	TextView t_medical_history;
	TextView t_special;
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

		setContentView(R.layout.cpu_2_2_1_data);	
		Intent intent = getIntent();
		s_user_id = intent.getStringExtra("user_id");	
		s_user_pw = intent.getStringExtra("user_pw");

		t_user_name = (TextView)findViewById(R.id.user_name);
		t_medical_num = (TextView)findViewById(R.id.call_medical_num);
		t_social_num = (TextView)findViewById(R.id.call_social_num);
		t_product_num = (TextView)findViewById(R.id.call_product_num);
		t_relative1 = (TextView)findViewById(R.id.call_relative1);
		t_emergency_num1 = (TextView)findViewById(R.id.call_emergency_num1);
		t_relative2 = (TextView)findViewById(R.id.call_relative2);
		t_emergency_num2 = (TextView)findViewById(R.id.call_emergency_num2);
		t_address = (TextView)findViewById(R.id.call_address);
		t_medicine = (TextView)findViewById(R.id.call_medicine);
		t_medical_history = (TextView)findViewById(R.id.call_medical_history);
		t_special = (TextView)findViewById(R.id.call_special);

		t_user_id = (TextView)findViewById(R.id.user_id);
		t_user_id.setText(s_user_id + "님, 반갑습니다.");
		
		strUrl = "http://220.67.113.124/CPU/mobile/myprofile2.php?id="+s_user_id+"&pass="+s_user_pw;
//		new DownloadWebpageTask().execute(strUrl);
		
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
						if(i==3) {data=word; t_product_num.setText(word);}
//						if(i==3) t_product_num.setText(word);
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
		}catch(Exception e){
			e.printStackTrace();
		}

	}


	class scworker extends Thread {  

		public scworker(){


		}
		public void run() {
			try {
				socket = new Socket("220.67.113.124", 9000); 
				//data = t_product_num.getText().toString();	   			
				socket_out = new PrintWriter(socket.getOutputStream(), true);
				data=data+'&';
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
							intent = new Intent(CPU_2_2_1_Data.this, MyAlert.class);
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
		Intent edit_data = new Intent(this, CPU_2_2_1_Edit_Data.class);
		edit_data.putExtra("user_id", s_user_id);
		edit_data.putExtra("user_pw", s_user_pw);
		startActivity(edit_data);
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
		Intent mypage = new Intent(this, CPU_2_2_1_Data.class);
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
