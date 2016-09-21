
package com.example.protector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
public class CPU_3_2_0_Patient_List extends Activity {

	String s_doctor_id;
	String s_doctor_pw;
	TextView t_doctor_id;

	TextView t_patient1_name;
	TextView t_patient1_birth;
	TextView t_patient1_product_num;

	TextView t_patient2_name;
	TextView t_patient2_birth;
	TextView t_patient2_product_num;

	TextView t_patient3_name;
	TextView t_patient3_birth;
	TextView t_patient3_product_num;

	TextView t_patient4_name;
	TextView t_patient4_birth;
	TextView t_patient4_product_num;

	TextView t_patient5_name;
	TextView t_patient5_birth;
	TextView t_patient5_product_num;

	String urlPath;
	String urlPath_num;
	//	String urlPath_num1;
	//	String urlPath_num2;
	//	String urlPath_num3;
	//	String urlPath_num4;
	//	String urlPath_num5;
	String pageContents = "";
	StringBuilder contents = new StringBuilder();
	public static String[] array_product_num;
	//	public static String[] array_name;
	//	public static String[] array_birth;

	String s_patient_product_num;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_3_2_0_patient_list);

		Intent intent = getIntent();
		s_doctor_id = intent.getStringExtra("doctor_id");	
		s_doctor_pw = intent.getStringExtra("doctor_pw");

		t_doctor_id = (TextView)findViewById(R.id.user_id);
		t_doctor_id.setText(s_doctor_id + "님, 반갑습니다.");

		urlPath = "http://220.67.113.124/CPU/mobile//patient.php?userid="+s_doctor_id+"&mode=3&pass="+s_doctor_pw;

		t_patient1_name = (TextView)findViewById(R.id.patient1_name);
		t_patient1_birth = (TextView)findViewById(R.id.patient1_birth);
		t_patient1_product_num = (TextView)findViewById(R.id.patient1_product_num);

		t_patient2_name = (TextView)findViewById(R.id.patient2_name);
		t_patient2_birth = (TextView)findViewById(R.id.patient2_birth);
		t_patient2_product_num = (TextView)findViewById(R.id.patient2_product_num);

		t_patient3_name = (TextView)findViewById(R.id.patient3_name);
		t_patient3_birth = (TextView)findViewById(R.id.patient3_birth);
		t_patient3_product_num = (TextView)findViewById(R.id.patient3_product_num);

		t_patient4_name = (TextView)findViewById(R.id.patient4_name);
		t_patient4_birth = (TextView)findViewById(R.id.patient4_birth);
		t_patient4_product_num = (TextView)findViewById(R.id.patient4_product_num);

		t_patient5_name = (TextView)findViewById(R.id.patient5_name);
		t_patient5_birth = (TextView)findViewById(R.id.patient5_birth);
		t_patient5_product_num = (TextView)findViewById(R.id.patient5_product_num);

		//		t_patient1_product_num.setText("제품번호 : " + s_patient_product_num);


		parse_list(urlPath);	

		t_patient1_product_num.setText("제품번호 : " + array_product_num[0]);
		t_patient2_product_num.setText("제품번호 : " + array_product_num[1]);
		t_patient3_product_num.setText("제품번호 : " + array_product_num[2]);
		t_patient4_product_num.setText("제품번호 : " + array_product_num[3]);
		t_patient5_product_num.setText("제품번호 : " + array_product_num[4]);

		//		urlPath_num1 = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[0];
		//		new DownloadWebpageTask().execute(urlPath_num1);
		//		urlPath_num2 = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[1];
		//		new DownloadWebpageTask().execute(urlPath_num2);
		//		urlPath_num3 = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[2];
		//		new DownloadWebpageTask().execute(urlPath_num3);
		//		urlPath_num4 = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[3];
		//		new DownloadWebpageTask().execute(urlPath_num4);
		//		urlPath_num5 = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[4];
		//		new DownloadWebpageTask().execute(urlPath_num5);


		//		t_patient1_name.setText("이름 : " + array_name[0]);
		//		t_patient2_name.setText("이름 : " + array_name[1]);
		//		t_patient3_name.setText("이름 : " + array_name[2]);
		//		t_patient4_name.setText("이름 : " + array_name[3]);
		//		t_patient5_name.setText("이름 : " + array_name[4]);
		//		
		//		t_patient1_birth.setText("주민번호 : " + array_birth[0]);
		//		t_patient2_birth.setText("주민번호 : " + array_birth[1]);
		//		t_patient3_birth.setText("주민번호 : " + array_birth[2]);
		//		t_patient4_birth.setText("주민번호 : " + array_birth[3]);
		//		t_patient5_birth.setText("주민번호 : " + array_birth[4]);


	}

	public void parse_list(String urlPath){
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
			array_product_num = new String[100];
			for(int j=60; j<(result.length()-10); j++)
			{
				String word2=result.substring(j,j+7);
				if(word2.equals("등록된 제품<"))
				{				 		
					array_product_num[cnt]=result.substring(j-13,j);
					//					urlPath_num = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[cnt];
					//					parse_data(urlPath_num);
					////					new DownloadWebpageTask().execute(urlPath_num1);
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



	//	public void parse_data(String urlPath){
	//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads()
	//				.detectDiskWrites().detectNetwork().penaltyLog().build();
	//		StrictMode.setThreadPolicy(policy);
	//		try{
	//
	//			URL url = new URL(urlPath);
	//			URLConnection con = (URLConnection)url.openConnection();
	//			InputStreamReader reader = new InputStreamReader (con.getInputStream(), "utf-8");
	//
	//			BufferedReader buff = new BufferedReader(reader);
	//			contents = new StringBuilder();
	//			while((pageContents = buff.readLine())!=null){
	//				//System.out.println(pageContents);             
	//				contents.append(pageContents);
	//				contents.append("\r\n");
	//			}
	//			buff.close();
	//			String result=contents.toString();
	//			int cnt =0;
	//			array_name = new String[100];
	////			array_birth = new String[100];
	//			
	//			for(int j=0; j<(result.length()-10); j++)
	//			{
	//				String word2=result.substring(j,j+6);
	////				String word3=result.substring(j+6,j+6+12);
	//				
	//				if((urlPath=="http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[0])&&(word2.equals("name=<")))
	//				{				 		
	//					array_name[0]=result.substring(j-13,j);
	//					
	//				}
	////				if(word3.equals("social_num=<"))
	////				{				 		
	////					array_birth[cnt]=result.substring(j-13,j);
	////					cnt++;
	////				}
	//
	//			}
	//		}catch(Exception e){
	//			e.printStackTrace();
	//		}
	//	}




	//
	//	private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
	//
	//		@Override
	//		protected String doInBackground(String... urls) {
	//			try{
	//				return (String)downloadUrl((String)urls[0]);
	//			} catch (IOException e) {
	//				return "error";
	//			}
	//		}
	//
	//		protected void onPostExecute(String result) {
	//			String word="";
	//			int pt_start=-1;
	//			int pt_end=-1;
	//			int i;
	////			String tag_start[] = {"name=","social_num=", "product_num="};
	////			String tag_end[] = {"=name","=social_num", "=product_num"};
	//			
	//			String tag_start[] = {"name=","social_num="};
	//			String tag_end[] = {"=name","=social_num"};
	//
	////			for(int p=0; p<5; p++){
	//				for(i=0; i<2; i++) {
	//
	//					pt_start= result.indexOf(tag_start[i]);
	//					if(pt_start != -1) {
	//						pt_end = result.indexOf(tag_end[i]);
	//
	//						if(pt_end != -1) {
	//							word = result.substring(pt_start + tag_start[i].length(), pt_end);
	//							if(i==0) array_name[p]= word;
	//							else if(i==1) array_birth[p] = word;
	////							else if(i==2) array_product_num[i] = word;
	//						} else
	//							t_patient1_product_num.setText("데이터가 없습니다!");    			
	//					} 
	//				}
	//				
	//	//		}
	//		}
	//
	//		private String downloadUrl(String myurl) throws IOException {
	//			HttpURLConnection conn = null;
	//			try {
	//				URL url = new URL(myurl);
	//				conn = (HttpURLConnection)url.openConnection();
	//				BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
	//				BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
	//				String line = null;
	//				String page = "";
	//				while((line = bufreader.readLine()) != null) {
	//					page += line;
	//				}
	//
	//				return page;
	//			} finally {
	//				conn.disconnect();
	//			}
	//		}
	//	}
	//
	public void add_patient(View v){
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent add_patient = new Intent(this, CPU_3_2_0_Add_Patient.class);
		add_patient.putExtra("doctor_id", s_doctor_id);
		add_patient.putExtra("doctor_pw", s_doctor_pw);
		startActivity(add_patient);

		this.onDestroy();

	}

	public void data_patient1(View v){
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
		data_patient.putExtra("doctor_id", s_doctor_id);
		data_patient.putExtra("doctor_pw", s_doctor_pw);
		data_patient.putExtra("patient_product_num", array_product_num[0]);
		startActivity(data_patient);
		this.onDestroy();		

	}
	public void data_patient2(View v){
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
		data_patient.putExtra("doctor_id", s_doctor_id);
		data_patient.putExtra("doctor_pw", s_doctor_pw);
		data_patient.putExtra("patient_product_num", array_product_num[1]);
		startActivity(data_patient);
		this.onDestroy();


	}
	public void data_patient3(View v){
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
		data_patient.putExtra("doctor_id", s_doctor_id);
		data_patient.putExtra("doctor_pw", s_doctor_pw);
		data_patient.putExtra("patient_product_num", array_product_num[2]);
		startActivity(data_patient);
		this.onDestroy();


	}
	public void data_patient4(View v){
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
		data_patient.putExtra("doctor_id", s_doctor_id);
		data_patient.putExtra("doctor_pw", s_doctor_pw);
		data_patient.putExtra("patient_product_num", array_product_num[3]);
		startActivity(data_patient);
		this.onDestroy();


	}
	public void data_patient5(View v){
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
		data_patient.putExtra("doctor_id", s_doctor_id);
		data_patient.putExtra("doctor_pw", s_doctor_pw);
		data_patient.putExtra("patient_product_num", array_product_num[4]);
		startActivity(data_patient);
		this.onDestroy();


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

}//package com.example.protector;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.os.Vibrator;
//import android.util.Log;
//import android.view.View;
//import android.view.Window;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//public class CPU_3_2_0_Patient_List extends Activity {
//
//	String s_doctor_id;
//	String s_doctor_pw;
//	TextView t_doctor_id;
//
//
//	TextView t_patient1_name; 
//	TextView t_patient1_birth;
//	TextView t_patient1_product_num; 
//
//	TextView t_patient2_name;
//	TextView t_patient2_birth;
//	TextView t_patient2_product_num;
//
//	TextView t_patient3_name;
//	TextView t_patient3_birth;
//	TextView t_patient3_product_num;
//
//	TextView t_patient4_name;
//	TextView t_patient4_birth;
//	TextView t_patient4_product_num;
//
//	TextView t_patient5_name;
//	TextView t_patient5_birth;
//	TextView t_patient5_product_num;
//
//	String urlPath;	
//	String pageContents = "";
//	StringBuilder contents = new StringBuilder();
//
//	public static String[] urlPath_num;
//	public static String[] array_product_num;
//	public static String[] array_name= new String[100];
//	public static String[] array_birth= new String[100];
//	public static String[] array_sex= new String[100];
//	int cnt =0;
//	String s_patient_product_num;	
//
//	ListView list;
//	MyAdapter adapter;
//	private ArrayAdapter<MyData> myAdapter; 
//	ArrayList<MyData> arrData= new ArrayList<MyData>();
//
//
//	TextView no_patient;
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//		setContentView(R.layout.cpu_3_2_0_patient_list);
//
//		Intent intent = getIntent();
//		s_doctor_id = intent.getStringExtra("doctor_id");	
//		s_doctor_pw = intent.getStringExtra("doctor_pw");
//
//		t_doctor_id = (TextView)findViewById(R.id.user_id);
//		t_doctor_id.setText(s_doctor_id + "님, 반갑습니다.");
//
//		urlPath = "http://220.67.113.124/CPU/mobile//patient.php?userid="+s_doctor_id+"&mode=3&pass="+s_doctor_pw;
//		
//		parse_list(urlPath);	
//		//리스트에 보여줄 데이터를 세팅한다.
//		setData();
//
//		//어댑터 생성
//		adapter = new MyAdapter(this, arrData);
//
//		//리스트뷰에 어댑터 연결
//		list = (ListView)findViewById(R.id.list);
//
//		list.setAdapter(adapter);
//		/*
//      list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//          public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {      	//LongClick이라는 신호가 들어오면
//
//          	arrData.remove(position); //파라미터로 받아온 해당 포지션의 데이터 값을 삭제한다.
//              myAdapter.notifyDataSetChanged(); //어댑터를 업데이트해서 데이터가 바뀌었다는것을 인지하고 리스트 뷰로 그것을 나타내는 기능.
//              Toast.makeText(CPU_3_2_0_Patient_List.this,"삭제되었습니다." , Toast.LENGTH_SHORT).show(); 
//
//              return false; 
//
//          }
//
//      });*/
//		/*
//		t_patient1_product_num.setText("제품번호 : " + array_product_num[0]);
//		t_patient2_product_num.setText("제품번호 : " + array_product_num[1]);
//		t_patient3_product_num.setText("제품번호 : " + array_product_num[2]);
//		t_patient4_product_num.setText("제품번호 : " + array_product_num[3]);
//		t_patient5_product_num.setText("제품번호 : " + array_product_num[4]);
//
//		//		urlPath_num1 = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[0];
//		//		new DownloadWebpageTask().execute(urlPath_num1);
//		//		urlPath_num2 = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[1];
//		//		new DownloadWebpageTask().execute(urlPath_num2);
//		//		urlPath_num3 = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[2];
//		//		new DownloadWebpageTask().execute(urlPath_num3);
//		//		urlPath_num4 = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[3];
//		//		new DownloadWebpageTask().execute(urlPath_num4);
//		//		urlPath_num5 = "http://220.67.113.124/CPU/mobile/myprofile1.php?code=" + array_product_num[4];
//		//		new DownloadWebpageTask().execute(urlPath_num5);
//
//
//				t_patient1_name.setText("이름 : " + array_name[0]);
//				t_patient2_name.setText("이름 : " + array_name[1]);
//				t_patient3_name.setText("이름 : " + array_name[2]);
//				t_patient4_name.setText("이름 : " + array_name[3]);
//				t_patient5_name.setText("이름 : " + array_name[4]);
//
//				t_patient1_birth.setText("주민번호 : " + array_birth[0]);
//				t_patient2_birth.setText("주민번호 : " + array_birth[1]);
//				t_patient3_birth.setText("주민번호 : " + array_birth[2]);
//				t_patient4_birth.setText("주민번호 : " + array_birth[3]);
//				t_patient5_birth.setText("주민번호 : " + array_birth[4]);
//
//		 */
//
//
//	}
//	private void setData(){
//
//		for(int i=0; i<cnt; i++)
//		{
//			arrData.add(new MyData(array_name[i],array_birth[i], array_sex[i], array_product_num[i]));
//		}
//		if(arrData.size()==0)
//		{
//			no_patient=(TextView)findViewById(R.id.no_patient);
//			no_patient.setText("아래의 버튼을 통해 \n 환자등록을 해주시기 바랍니다.");
//		}
//	}
//
//	public void parse_list(String urlPath){
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads()
//				.detectDiskWrites().detectNetwork().penaltyLog().build();
//		StrictMode.setThreadPolicy(policy);
//		try{
//
//			URL url = new URL(urlPath);
//			URLConnection con = (URLConnection)url.openConnection();
//			InputStreamReader reader = new InputStreamReader (con.getInputStream(), "utf-8");
//
//			BufferedReader buff = new BufferedReader(reader);
//			contents = new StringBuilder();
//			while((pageContents = buff.readLine())!=null){
//				//System.out.println(pageContents);             
//				contents.append(pageContents);
//				contents.append("\r\n");
//			}
//			buff.close();
//			String result=contents.toString();
//			int k=0;
//			array_product_num = new String[100];
//			urlPath_num = new String[100];
//			for(int j=70; j<(result.length()-10); j++)
//			{
//				String word2=result.substring(j,j+8);
//				if(word2.equals(" 등록된 제품<"))
//				{		
//					for(int i=j; i>70; i--)
//					{
//						String word3=result.substring(i-6,i);
//						if(word3.equals("등록된 제품"))
//						{
//							k=i;	
//							break;
//						}							
//					}						
//					array_product_num[cnt]=result.substring(k,j);
//					urlPath_num[cnt] = "http://220.67.113.124/CPU/mobile/myprofile1.php?code="+array_product_num[cnt];
//
//					cnt++;
//				}		
//			}
//			parse_data(urlPath_num);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//
//
//	private String request(String urlStr) {
//		StringBuilder output = new StringBuilder();
//		try {
//			URL url = new URL(urlStr);
//			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//			if (conn != null) {
//				conn.setConnectTimeout(10000);
//				conn.setRequestMethod("GET");
//				conn.setDoInput(true);
//				conn.setDoOutput(true);
//
//				int resCode = conn.getResponseCode();
//				if (resCode == HttpURLConnection.HTTP_OK) {
//					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
//					String line = null;
//					while(true) {
//						line = reader.readLine();
//						if (line == null) {
//							break;
//						}
//						output.append(line + "\n");
//					}
//
//					reader.close();
//					conn.disconnect();
//				}
//			}
//		} catch(Exception ex) {
//			Log.e("SampleHTTP", "Exception in processing response.", ex);
//			ex.printStackTrace();
//		}
//
//		return output.toString();
//	}
//
//
//
//	public void parse_data(String urlPath_num[]){
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads()
//				.detectDiskWrites().detectNetwork().penaltyLog().build();
//		StrictMode.setThreadPolicy(policy);
//		for(int s=0; s<cnt; s++)
//		{
//			try{
//
//				URL url = new URL(urlPath_num[s]);
//				URLConnection con = (URLConnection)url.openConnection();
//				InputStreamReader reader = new InputStreamReader (con.getInputStream(), "utf-8");
//
//				BufferedReader buff = new BufferedReader(reader);
//				contents = new StringBuilder();
//				while((pageContents = buff.readLine())!=null){
//					//System.out.println(pageContents);             
//					contents.append(pageContents);
//					contents.append("\r\n");
//				}
//				buff.close();
//				String result=contents.toString();
//
//				int k=0;
//
//
//				for(int j=0; j<(result.length()-10); j++)
//				{
//					String word2=result.substring(j,j+5);
//					String word3=result.substring(j,j+11);
//					String word4=result.substring(j+12,j+14);
//
//					if(word2.equals("name="))
//					{		
//
//						for(int i=j+5; i<j+10; i++)
//						{
//							String word_name=result.substring(i,i+5);
//							if(word_name.equals("=name"))
//							{
//								k=i;	
//								break;
//							}							
//						}						
//						array_name[s]=result.substring(j+5,k);						
//					}
//					if(word3.equals("social_num="))
//					{				 		
//						array_birth[s]=result.substring(j+11,j+17);
//							
//					}
//
//					if((word3.equals("social_num="))&&((word4.equals("-2"))||(word4.equals("-4"))))
//					{
//						array_sex[s]="여자";
//					}
//					if((word3.equals("social_num="))&&((word4.equals("-1"))||(word4.equals("-3"))))
//					{
//						array_sex[s]="남자";
//					}
//
//				}
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//
//
//	//
//	//	private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
//	//
//	//		@Override
//	//		protected String doInBackground(String... urls) {
//	//			try{
//	//				return (String)downloadUrl((String)urls[0]);
//	//			} catch (IOException e) {
//	//				return "error";
//	//			}
//	//		}
//	//
//	//		protected void onPostExecute(String result) {
//	//			String word="";
//	//			int pt_start=-1;
//	//			int pt_end=-1;
//	//			int i;
//	////			String tag_start[] = {"name=","social_num=", "product_num="};
//	////			String tag_end[] = {"=name","=social_num", "=product_num"};
//	//			
//	//			String tag_start[] = {"name=","social_num="};
//	//			String tag_end[] = {"=name","=social_num"};
//	//
//	////			for(int p=0; p<5; p++){
//	//				for(i=0; i<2; i++) {
//	//
//	//					pt_start= result.indexOf(tag_start[i]);
//	//					if(pt_start != -1) {
//	//						pt_end = result.indexOf(tag_end[i]);
//	//
//	//						if(pt_end != -1) {
//	//							word = result.substring(pt_start + tag_start[i].length(), pt_end);
//	//							if(i==0) array_name[p]= word;
//	//							else if(i==1) array_birth[p] = word;
//	////							else if(i==2) array_product_num[i] = word;
//	//						} else
//	//							t_patient1_product_num.setText("데이터가 없습니다!");    			
//	//					} 
//	//				}
//	//				
//	//	//		}
//	//		}
//	//
//	//		private String downloadUrl(String myurl) throws IOException {
//	//			HttpURLConnection conn = null;
//	//			try {
//	//				URL url = new URL(myurl);
//	//				conn = (HttpURLConnection)url.openConnection();
//	//				BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
//	//				BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
//	//				String line = null;
//	//				String page = "";
//	//				while((line = bufreader.readLine()) != null) {
//	//					page += line;
//	//				}
//	//
//	//				return page;
//	//			} finally {
//	//				conn.disconnect();
//	//			}
//	//		}
//	//	}
//	//
//	public void add_patient(View v){
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);		
//		Intent add_patient = new Intent(this, CPU_3_2_0_Add_Patient.class);
//		add_patient.putExtra("doctor_id", s_doctor_id);
//		add_patient.putExtra("doctor_pw", s_doctor_pw);
//		startActivity(add_patient);
//
//		this.onDestroy();
//
//	}
//	public void MyAdapter(View v){
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);		
//		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
//		data_patient.putExtra("doctor_id", s_doctor_id);
//		data_patient.putExtra("doctor_pw", s_doctor_pw);
//		this.onDestroy();		
//	}
//	/*
//	public void data_patient(View v){
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);		
//		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
//		data_patient.putExtra("doctor_id", s_doctor_id);
//		data_patient.putExtra("doctor_pw", s_doctor_pw);
//		data_patient.putExtra("patient_product_num", array_product_num[0]);
//		startActivity(data_patient);
//		this.onDestroy();		
//	}*/
//	/*
//
//	public void data_patient2(View v){
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);		
//		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
//		data_patient.putExtra("doctor_id", s_doctor_id);
//		data_patient.putExtra("doctor_pw", s_doctor_pw);
//		data_patient.putExtra("patient_product_num", array_product_num[1]);
//		startActivity(data_patient);
//		this.onDestroy();
//
//
//	}
//	public void data_patient3(View v){
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);		
//		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
//		data_patient.putExtra("doctor_id", s_doctor_id);
//		data_patient.putExtra("doctor_pw", s_doctor_pw);
//		data_patient.putExtra("patient_product_num", array_product_num[2]);
//		startActivity(data_patient);
//		this.onDestroy();
//
//
//	}
//	public void data_patient4(View v){
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);		
//		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
//		data_patient.putExtra("doctor_id", s_doctor_id);
//		data_patient.putExtra("doctor_pw", s_doctor_pw);
//		data_patient.putExtra("patient_product_num", array_product_num[3]);
//		startActivity(data_patient);
//		this.onDestroy();
//
//
//	}
//	public void data_patient5(View v){
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);		
//		Intent data_patient = new Intent(this, CPU_3_2_1_Data.class);
//		data_patient.putExtra("doctor_id", s_doctor_id);
//		data_patient.putExtra("doctor_pw", s_doctor_pw);
//		data_patient.putExtra("patient_product_num", array_product_num[4]);
//		startActivity(data_patient);
//		this.onDestroy();
//
//
//	}
//	 */
//
//	protected void onDestroy(){		
//		super.onDestroy();
//		this.finish();
//	}
//
//	public void main(View v){
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);				
//		Intent main = new Intent(this, ActivityMain.class);
//		startActivity(main);
//		this.onDestroy();
//	}
//	public void introduce_product(View v){	
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);			
//		Intent introduce_product = new Intent(this, CPU_3_1_1_Maker.class);
//		introduce_product.putExtra("doctor_id", s_doctor_id);
//		introduce_product.putExtra("doctor_pw", s_doctor_pw);
//		startActivity(introduce_product);
//		this.onDestroy();
//	}
//	public void mypage(View v){		
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);		
//		Intent mypage = new Intent(this, CPU_3_2_0_Patient_List.class);
//		mypage.putExtra("doctor_id", s_doctor_id);
//		mypage.putExtra("doctor_pw", s_doctor_pw);
//		startActivity(mypage);
//		this.onDestroy();
//	}
//	public void service(View v){	
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);			
//		Intent service = new Intent(this, CPU_3_3_1_FAQ.class);
//		service.putExtra("doctor_id", s_doctor_id);
//		service.putExtra("doctor_pw", s_doctor_pw);
//		startActivity(service);
//		this.onDestroy();
//	}
//
//	public void mypage_patient(View v){		
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);		
//		Intent mypage_patient = new Intent(this, CPU_3_2_0_Patient_List.class);
//		mypage_patient.putExtra("doctor_id", s_doctor_id);
//		mypage_patient.putExtra("doctor_pw", s_doctor_pw);
//		startActivity(mypage_patient);
//		this.onDestroy();
//	}
//
//	public void mypage_doctor(View v){		
//		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//		vibe_add.vibrate(50);		
//		Intent mypage_doctor = new Intent(this, CPU_3_2_1_Data_Personal.class);
//		mypage_doctor.putExtra("doctor_id", s_doctor_id);
//		mypage_doctor.putExtra("doctor_pw", s_doctor_pw);
//		startActivity(mypage_doctor);
//		this.onDestroy();
//	}
//
//}





