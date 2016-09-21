package com.example.protector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CPU_3_3_1_FAQ extends Activity {
	private Activity activity;
	public static Context context; 
	String s_doctor_id;
	String s_doctor_pw;
	public String Number;
	public String Subject;
	public String Name;
	public String Date;
	public String Content;
	TextView t_doctor_id;
	TextView page;
	String urlPath;
	String urlPath2;
	String pageContents = "";
	StringBuilder contents = new StringBuilder();
	StringBuilder contents2 = new StringBuilder();
	private ArrayList<CPU_2_3_1_FAQ2> m_arr;
	private List_Adapter adapter;
	public static String[] number;
	public static String[] subject;
	public static String[] subject2;
	public static String[] name;
	public static String[] date;
	public static String[] content;
	public int rec;
	public int pageco;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_2_3_1_faq);	
		pageco=1;
		Intent intent = getIntent();
		s_doctor_id = intent.getStringExtra("doctor_id");	
		s_doctor_pw = intent.getStringExtra("doctor_pw");
		urlPath="http://220.67.113.124/CPU/mobile/listfap.php";
		t_doctor_id = (TextView)findViewById(R.id.user_id);
		page = (TextView)findViewById(R.id.page);
		t_doctor_id.setText(s_doctor_id + "´Ô, ¹Ý°©½À´Ï´Ù.");
		page.setText(Integer.toString(pageco));
		parse(urlPath);
		Intent i = getIntent();
		setList();
		Button btnDownload1 = (Button)findViewById(R.id.left);
		btnDownload1.setOnClickListener(myButtonClick1);

		Button btnDownload2 = (Button)findViewById(R.id.right);
		btnDownload2.setOnClickListener(myButtonClick2);

	}


	Button.OnClickListener myButtonClick1 = new Button.OnClickListener()
	{
		public void onClick(View v){	

			Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			vibe_add.vibrate(50);		

			if(pageco>1)    		
				pageco--;
			page.setText(Integer.toString(pageco));
			//String pageco2=Integer.toString(pageco);
			parse(urlPath+"?page="+pageco);
			setList();
		}
	};


	Button.OnClickListener myButtonClick2 = new Button.OnClickListener()
	{
		public void onClick(View v){
			Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			vibe_add.vibrate(50);	

			if(pageco<(rec/5+1))    		
				pageco++;
			page.setText(Integer.toString(pageco));
			parse(urlPath+"?page="+pageco);
			setList();

		}
	};

	private void setList(){
		m_arr = new ArrayList<CPU_2_3_1_FAQ2>();
		ListView lv = (ListView)findViewById(R.id.listView1);
		for(int i=0; i<5; i++)
		{		
			m_arr.add(new CPU_2_3_1_FAQ2(number[i],subject[i],name[i],date[i],content[i]));

		}
		adapter = new List_Adapter(CPU_3_3_1_FAQ.this, m_arr,CPU_3_3_1_FAQ.this);
		lv.setAdapter(adapter);
	}


	public void listUpdate(){
		adapter.notifyDataSetChanged();
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

			number = new String[100];
			name = new String[100];
			subject = new String[100];
			subject2 = new String[100];
			date = new String[100];
			content = new String[100];
			String record = "";
			String word = "";
			int pt_start = -1;
			int pt_end = -1;
			int cnt =0;
			int cnt2 =0;
			int[] k;
			k = new int[100];
			String tag_start = "record= ";
			String tag_end = "<br/>";
			pt_start = result.indexOf(tag_start);
			pt_end = result.indexOf(tag_end);
			record = result.substring(pt_start+8, pt_end);
			rec=Integer.parseInt(record);
			for(int j=50; j<(result.length()-5); j++)
			{
				String word2=result.substring(j,j+5);
				if(word2.equals("<br/>"))
				{
					cnt++;
					if((cnt%3)==2)
					{
						if(cnt==2)
							number[0]=result.substring(j-1,j);
						if(cnt>2)
							number[(cnt-2)/3]=result.substring(j-1,j);
					}
					if((cnt%3)==1&&(cnt>1))
					{	
						k[cnt/3-1]=j;
					}		     						
				}
			} 	
			for(int j=50; j<(result.length()-5); j++)
			{
				String word3=result.substring(j,j+5);
				if(word3.equals("</br>"))
				{
					cnt2++;
					if(cnt2%2==1)
					{	
						int x=j;
						while(x>50){	     								
							if(result.substring(x-1,x).equals(">"))
							{
								name[(cnt2-1)/2]=result.substring(x,j);
								break;
							}
							x--;	    	     							
						}
					}	     					
					else
					{
						date[cnt2/2-1]=result.substring(j-10,j);
						subject2[cnt2/2-1]=result.substring(j+5,k[cnt2/2-1]);
						subject[cnt2/2-1] = subject2[cnt2/2-1].replaceAll("&nbsp;", " ");

					}	     					
				}

			}	   
			for(int i=0; number[i]!=null; i++)
			{
				urlPath2="http://220.67.113.124/CPU/mobile/view.php?t=faq&num="+number[i];
				URL url2 = new URL(urlPath2);
				URLConnection con2 = (URLConnection)url2.openConnection();
				InputStreamReader reader2 = new InputStreamReader (con2.getInputStream(), "utf-8");

				BufferedReader buff2 = new BufferedReader(reader2);
				contents2 = new StringBuilder();
				while((pageContents = buff2.readLine())!=null){
					//System.out.println(pageContents);             
					contents2.append(pageContents);
					contents2.append("\r\n");
				}
				buff2.close();
				String result2=contents2.toString();
				tag_start = ")";
				tag_end = "</body>";
				pt_start = result2.indexOf(tag_start);
				pt_end = result2.indexOf(tag_end);
				word = result2.substring(pt_start+1, pt_end);
				content[i] = word.replaceAll("&nbsp;", " ");
				word=" ";
			}

		}catch(Exception e){
			e.printStackTrace();
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

	public void service_faq(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent service_faq = new Intent(this, CPU_3_3_1_FAQ.class);
		service_faq.putExtra("doctor_id", s_doctor_id);
		service_faq.putExtra("doctor_pw", s_doctor_pw);
		startActivity(service_faq);
		this.onDestroy();
	}
	public void service_onetoone(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent service_onetoone = new Intent(this, CPU_3_3_2_1TO1.class);
		service_onetoone.putExtra("doctor_id", s_doctor_id);
		service_onetoone.putExtra("doctor_pw", s_doctor_pw);
		startActivity(service_onetoone);
		this.onDestroy();
	}
	public void service_notice(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent service_notice = new Intent(this, CPU_3_3_3_Notice.class);
		service_notice.putExtra("doctor_id", s_doctor_id);
		service_notice.putExtra("doctor_pw", s_doctor_pw);
		startActivity(service_notice);
		this.onDestroy();
	}

}


