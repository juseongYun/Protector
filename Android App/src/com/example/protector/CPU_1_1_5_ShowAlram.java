package com.example.protector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CPU_1_1_5_ShowAlram extends Activity {
	String s_product_num;
	TextView t_product_num;
	TextView alram1;
	TextView alram2;
	TextView alram3;
	TextView alram4;
	TextView alram5;
	public static String hour;
	public static String minute;
	public static String[] time;
	String urlPath;
	String pageContents = "";
	StringBuilder contents = new StringBuilder();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(com.example.protector.R.layout.cpu_1_1_5_showalram);   

		Intent intent = getIntent();
		s_product_num = intent.getStringExtra("product_num");
		t_product_num = (TextView)findViewById(R.id.product_num);
		t_product_num.setText(s_product_num + "��, �ݰ����ϴ�.");
		urlPath = "http://220.67.113.124/CPU/mobile/alarm.php?mode=1&product_num="+s_product_num;
		alram1 = (TextView)findViewById(R.id.alram1);
		alram2 = (TextView)findViewById(R.id.alram2);
		alram3 = (TextView)findViewById(R.id.alram3);
		alram4 = (TextView)findViewById(R.id.alram4);
		alram5 = (TextView)findViewById(R.id.alram5);
		parse(urlPath);      
		alram1.setText(time[0]);
		alram2.setText(time[1]);
		alram3.setText(time[2]);
		alram4.setText(time[3]);
		alram5.setText(time[4]);


		Spinner s_hour = (Spinner) findViewById(R.id.spinner_hour);
		s_hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				hour=(String)parent.getItemAtPosition(position);
				//alram5.setText(parent.getItemAtPosition(position)+" ");
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});

		Spinner s_minute = (Spinner) findViewById(R.id.spinner_minute);
		s_minute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				minute=(String)parent.getItemAtPosition(position);
				//alram5.setText(parent.getItemAtPosition(position)+" ");
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});

		Button btn1 = (Button)findViewById(R.id.insert);
		btn1.setOnClickListener(insert_Button);

		alram1.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{   		
				Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				vibe_add.vibrate(50);
				AlertDialog.Builder dlg = new AlertDialog.Builder(CPU_1_1_5_ShowAlram.this);
				dlg.setMessage("�ش��� �� �˶��ð��� ����Ʈ���� �����Ͻðڽ��ϱ�?");
				dlg.setTitle("�ȳ�");
				dlg.setPositiveButton("Ȯ��",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){
						String hour1=time[0].substring(0,2);
						String minute1=time[0].substring(5,7);
						request("http://220.67.113.124/CPU/mobile/delete_alarm.php?mode=1&product_num="+s_product_num+"&hour="+hour1+"&minute="+minute1);   
						parse(urlPath);
						alram1.setText(time[0]);
						alram2.setText(time[1]);
						alram3.setText(time[2]);
						alram4.setText(time[3]);
						alram5.setText(time[4]);
						// finish();
					}
				});
				dlg.setNegativeButton("���",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){		
						Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						vibe_add.vibrate(50);
						parse(urlPath);
						alram1.setText(time[0]);
						alram2.setText(time[1]);
						alram3.setText(time[2]);
						alram4.setText(time[3]);
						alram5.setText(time[4]);
						// finish();
					}
				});
				dlg.show();
			}
		}); 

		alram2.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{   
				AlertDialog.Builder dlg = new AlertDialog.Builder(CPU_1_1_5_ShowAlram.this);
				dlg.setMessage("�ش��� �� �˶��ð��� ����Ʈ���� �����Ͻðڽ��ϱ�?");
				dlg.setTitle("�ȳ�");
				dlg.setPositiveButton("Ȯ��",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){		
						Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						vibe_add.vibrate(50);
						String hour2=time[1].substring(0,2);
						String minute2=time[1].substring(5,7);
						request("http://220.67.113.124/CPU/mobile/delete_alarm.php?mode=1&product_num="+s_product_num+"&hour="+hour2+"&minute="+minute2);                   
						parse(urlPath);
						alram1.setText(time[0]);
						alram2.setText(time[1]);
						alram3.setText(time[2]);
						alram4.setText(time[3]);
						alram5.setText(time[4]);
						// finish();
					}
				});
				dlg.setNegativeButton("���",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){		
						Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						vibe_add.vibrate(50);
						parse(urlPath);
						alram1.setText(time[0]);
						alram2.setText(time[1]);
						alram3.setText(time[2]);
						alram4.setText(time[3]);
						alram5.setText(time[4]);
						// finish();
					}
				});
				dlg.show();
			}
		}); 

		alram3.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{   
				AlertDialog.Builder dlg = new AlertDialog.Builder(CPU_1_1_5_ShowAlram.this);
				dlg.setMessage("�ش��� �� �˶��ð��� ����Ʈ���� �����Ͻðڽ��ϱ�?");
				dlg.setTitle("�ȳ�");
				dlg.setPositiveButton("Ȯ��",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){		
						Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						vibe_add.vibrate(50);
						String hour3=time[2].substring(0,2);
						String minute3=time[2].substring(5,7);
						request("http://220.67.113.124/CPU/mobile/delete_alarm.php?mode=1&product_num="+s_product_num+"&hour="+hour3+"&minute="+minute3);                      
						parse(urlPath);
						alram1.setText(time[0]);
						alram2.setText(time[1]);
						alram3.setText(time[2]);
						alram4.setText(time[3]);
						alram5.setText(time[4]);
						// finish();
					}
				});
				dlg.setNegativeButton("���",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){		
						Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						vibe_add.vibrate(50);
						parse(urlPath);
						alram1.setText(time[0]);
						alram2.setText(time[1]);
						alram3.setText(time[2]);
						alram4.setText(time[3]);
						alram5.setText(time[4]);
						// finish();
					}
				});
				dlg.show();
			}
		}); 
		alram4.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{   
				AlertDialog.Builder dlg = new AlertDialog.Builder(CPU_1_1_5_ShowAlram.this);
				dlg.setMessage("�ش��� �� �˶��ð��� ����Ʈ���� �����Ͻðڽ��ϱ�?");
				dlg.setTitle("�ȳ�");
				dlg.setPositiveButton("Ȯ��",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){		
						Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						vibe_add.vibrate(50);
						String hour4=time[3].substring(0,2);
						String minute4=time[3].substring(5,7);
						request("http://220.67.113.124/CPU/mobile/delete_alarm.php?mode=1&product_num="+s_product_num+"&hour="+hour4+"&minute="+minute4);                      
						parse(urlPath);
						alram1.setText(time[0]);
						alram2.setText(time[1]);
						alram3.setText(time[2]);
						alram4.setText(time[3]);
						alram5.setText(time[4]);
						// finish();
					}
				});
				dlg.setNegativeButton("���",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){		
						Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						vibe_add.vibrate(50);
						parse(urlPath);
						alram1.setText(time[0]);
						alram2.setText(time[1]);
						alram3.setText(time[2]);
						alram4.setText(time[3]);
						alram5.setText(time[4]);
						// finish();
					}
				});
				dlg.show();
			}
		}); 
		alram5.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{   
				AlertDialog.Builder dlg = new AlertDialog.Builder(CPU_1_1_5_ShowAlram.this);
				dlg.setMessage("�ش��� �� �˶��ð��� ����Ʈ���� �����Ͻðڽ��ϱ�?");
				dlg.setTitle("�ȳ�");
				dlg.setPositiveButton("Ȯ��",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){		
						Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						vibe_add.vibrate(50);
						String hour5=time[4].substring(0,2);
						String minute5=time[4].substring(5,7);
						request("http://220.67.113.124/CPU/mobile/delete_alarm.php?mode=1&product_num="+s_product_num+"&hour="+hour5+"&minute="+minute5);                      
						parse(urlPath);
						alram1.setText(time[0]);
						alram2.setText(time[1]);
						alram3.setText(time[2]);
						alram4.setText(time[3]);
						alram5.setText(time[4]);
					}
				});
				dlg.setNegativeButton("���",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){		
						Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						vibe_add.vibrate(50);
						parse(urlPath);
						alram1.setText(time[0]);
						alram2.setText(time[1]);
						alram3.setText(time[2]);
						alram4.setText(time[3]);
						alram5.setText(time[4]);      
					}
				});
				dlg.show();

			}
		}); 

	}
	Button.OnClickListener insert_Button = new Button.OnClickListener()
	{
		public void onClick(View v)
		{   		
			Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			vibe_add.vibrate(50);
			if(time[4]==null)
			{

				request("http://220.67.113.124/CPU/mobile/insert_alarm.php?mode=1&product_num="+s_product_num+"&hour="+hour+"&minute="+minute);
				parse(urlPath);
				alram1.setText(time[0]);
				alram2.setText(time[1]);
				alram3.setText(time[2]);
				alram4.setText(time[3]);
				alram5.setText(time[4]);
			}
			else
			{
				AlertDialog.Builder dlg = new AlertDialog.Builder(CPU_1_1_5_ShowAlram.this);
				dlg.setMessage("�� �˸� �ð��� �ִ� 5������ ���� �����մϴ�.\n�Ʒ��� ����Ʈ�� ���� ��, �߰����ּ���.");
				dlg.setTitle("�ȳ�");
				dlg.setNegativeButton("Ȯ��",null);
				dlg.show();
			}


		}
	};
	/*
   Button.OnClickListener delete_Button = new Button.OnClickListener()
   {
      public void onClick(View v)
      {
         request("http://220.67.113.124/CPU/mobile/delete_alarm.php?product_num=0000&hour="+hour+"&minute="+minute);
         parse(urlPath);
         alram1.setText(time[0]);
         alram2.setText(time[1]);
         alram3.setText(time[2]);
         alram4.setText(time[3]);
         alram5.setText(time[4]);
      }
   };*/

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
			int cnt =0;
			time = new String[100];
			for(int j=60; j<(result.length()-10); j++)
			{
				String word2=result.substring(j,j+5);
				if(word2.equals("�˸��ð�<"))
				{                   
					time[cnt]=result.substring(j-7,j);
					cnt++;
				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private String request(String urlStr) {
		StringBuilder output = new StringBuilder();
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			if (conn != null) {
				conn.setConnectTimeout(10000);
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);

				int resCode = conn.getResponseCode();
				if (resCode == HttpURLConnection.HTTP_OK) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
					String line = null;
					while(true) {
						line = reader.readLine();
						if (line == null) {
							break;
						}
						output.append(line + "\n");
					}

					reader.close();
					conn.disconnect();
				}
			}
		} catch(Exception ex) {
			Log.e("SampleHTTP", "Exception in processing response.", ex);
			ex.printStackTrace();
		}

		return output.toString();
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

	public void show_photo(View v){  		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);    
		Intent show_photo = new Intent(this, CPU_1_1_4_ShowPhoto.class);
		show_photo.putExtra("product_num", s_product_num);
		startActivity(show_photo);
		this.onDestroy();
	}


}