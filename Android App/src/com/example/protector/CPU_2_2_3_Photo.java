package com.example.protector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class CPU_2_2_3_Photo extends Activity {

	String s_user_id;
	String s_user_pw;
	TextView t_user_id;
	public static String[] photo;
	public static String[] photo2;
	public static String[] photo3;
	public static String[] photo4;
	public static String[] photo5;
	String urlPath;
	TextView page;
	String pageContents = "";
	StringBuilder contents = new StringBuilder();
	String[] photoname;
	public int rec;
	public int pageco;

	private static String URL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cpu_2_2_3_photo);	
		Intent intent = getIntent();
		pageco=1;
		s_user_id = intent.getStringExtra("user_id");	
		s_user_pw = intent.getStringExtra("user_pw");
		t_user_id = (TextView)findViewById(R.id.user_id);
		t_user_id.setText(s_user_id + "´Ô, ¹Ý°©½À´Ï´Ù.");
		page = (TextView)findViewById(R.id.page);
		page.setText(Integer.toString(pageco));
		urlPath = "http://220.67.113.124/CPU/mobile/image.php?userid="+s_user_id+"&mode=2&pass="+s_user_pw;
		Button btnDownload1 = (Button)findViewById(R.id.left);
		btnDownload1.setOnClickListener(myButtonClick1);

		Button btnDownload2 = (Button)findViewById(R.id.right);
		btnDownload2.setOnClickListener(myButtonClick2);
		/*
	        Button btnDownload3 = (Button)findViewById(R.id.btnButton3);
	        btnDownload3.setOnClickListener(myButtonClick3);

	        Button btnDownload4 = (Button)findViewById(R.id.btnButton4);
	        btnDownload4.setOnClickListener(myButtonClick4);

	        Button btnDownload5 = (Button)findViewById(R.id.btnButton5);
	        btnDownload5.setOnClickListener(myButtonClick5);
		 */
		photoname=parse(urlPath);	
		ImageAdapter imgAdapter = new ImageAdapter(photoname, this); 
		GridView gv = (GridView)findViewById(R.id.gvGridView);
		gv.setAdapter(imgAdapter);

	}
	Button.OnClickListener myButtonClick1 = new Button.OnClickListener()
	{
		public void onClick(View v)
		{		
			Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			vibe_add.vibrate(50);
			if(pageco>1)    		
				pageco--;
			page.setText(Integer.toString(pageco));
			GridView gv = (GridView)findViewById(R.id.gvGridView);
			photoname=parse(urlPath+"&page="+pageco);	    		 
			ImageAdapter imgAdapter = new ImageAdapter(photoname, CPU_2_2_3_Photo.this);
			gv.setAdapter(imgAdapter);
		}
	};

	Button.OnClickListener myButtonClick2 = new Button.OnClickListener()
	{
		public void onClick(View v)
		{		
			Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			vibe_add.vibrate(50);
			if(pageco<(photoname.length)/4+1)    		
				pageco++;
			page.setText(Integer.toString(pageco));
			photoname=parse(urlPath+"&page="+pageco);
			GridView gv = (GridView)findViewById(R.id.gvGridView);
			ImageAdapter imgAdapter = new ImageAdapter(photoname, CPU_2_2_3_Photo.this);
			gv.setAdapter(imgAdapter);
		}
	};

	public String[] parse(String urlPath){
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

			photo = new String[20];
			String word = "";
			int pt_start = -1;
			int pt_end = -1;
			String tag_start = "2016";
			String tag_end = "<br/>";
			pt_start = result.indexOf(tag_start);
			for(int i = 0;i < (result.length()-70)/30;i++)
			{
				if(pt_start != -1){
					pt_end = result.indexOf(tag_end);
					if(pt_end != -1){
						word = result.substring(pt_start+30*i , pt_end+30*i);
						photo[i]="http://220.67.113.124/CPU/greet/data/"+word;
						//tv.append("ÆÄ½Ì °á°ú : " + word + "pt_start:"+pt_start+"pt_end"+pt_end);
						//System.out.println(photo[i]);
					}
					else{
						photo=null;
						break;
					} 
					//tv.append("¾ø´Ù");
				}


			}
			int cnt=0;
			while(photo[cnt]!=null)
			{
				cnt++;
			}

			photoname = new String[cnt];
			for(int i=0; i<cnt;i++)

			{
				photoname[i]=photo[i];
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return photoname;
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
