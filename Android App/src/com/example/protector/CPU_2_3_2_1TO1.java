package com.example.protector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CPU_2_3_2_1TO1 extends Activity {

	String s_user_id;
	String s_user_pw;
	TextView t_user_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_2_3_2_1to1);	

		Intent intent = getIntent();
		s_user_id = intent.getStringExtra("user_id");	
		s_user_pw = intent.getStringExtra("user_pw");

		t_user_id = (TextView)findViewById(R.id.user_id);
		t_user_id.setText(s_user_id + "´Ô, ¹Ý°©½À´Ï´Ù.");
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

	public void service_faq(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent service_faq = new Intent(this, CPU_2_3_1_FAQ.class);
		service_faq.putExtra("user_id", s_user_id);
		service_faq.putExtra("user_pw", s_user_pw);
		startActivity(service_faq);
		this.onDestroy();
	}
	public void service_onetoone(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent service_onetoone = new Intent(this, CPU_2_3_2_1TO1.class);
		service_onetoone.putExtra("user_id", s_user_id);
		service_onetoone.putExtra("user_pw", s_user_pw);
		startActivity(service_onetoone);
		this.onDestroy();
	}
	public void service_notice(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);
		Intent service_notice = new Intent(this, CPU_2_3_3_Notice.class);
		service_notice.putExtra("user_id", s_user_id);
		service_notice.putExtra("user_pw", s_user_pw);
		startActivity(service_notice);
		this.onDestroy();
	}
}
