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

public class CPU_1_1_4_ShowPhoto extends Activity {

	String s_product_num;
	TextView t_product_num;

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

		setContentView(com.example.protector.R.layout.cpu_1_1_4_showphoto);      
		Intent intent = getIntent();
		s_product_num = intent.getStringExtra("product_num");

		t_product_num = (TextView)findViewById(com.example.protector.R.id.product_num);
		t_product_num.setText(s_product_num + "´Ô, ¹Ý°©½À´Ï´Ù.");
		pageco=1;
		page = (TextView)findViewById(R.id.page);
		page.setText(Integer.toString(pageco));
		urlPath = "http://220.67.113.124/CPU/mobile/image.php?product_num="+s_product_num+"&mode=1";
		Button btnDownload1 = (Button)findViewById(R.id.left);
		btnDownload1.setOnClickListener(myButtonClick1);

		Button btnDownload2 = (Button)findViewById(R.id.right);
		btnDownload2.setOnClickListener(myButtonClick2);

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
			ImageAdapter imgAdapter = new ImageAdapter(photoname, CPU_1_1_4_ShowPhoto.this);
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
			ImageAdapter imgAdapter = new ImageAdapter(photoname, CPU_1_1_4_ShowPhoto.this);
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
					}
					else{
						photo=null;
						break;
					} 
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

	public void show_medicaldata(View v){   		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);   
		Intent show_medicaldata = new Intent(this, CPU_1_1_2_ShowData.class);
		show_medicaldata.putExtra("product_num", s_product_num);
		startActivity(show_medicaldata);
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
	public void show_alram(View v){      		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);
		Intent show_alram = new Intent(this, CPU_1_1_5_ShowAlram.class);
		show_alram.putExtra("product_num", s_product_num);
		startActivity(show_alram);
		this.onDestroy();
	}

}