package com.example.protector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CPU_3_1_3_IntroduceProduct extends Activity {

	String s_doctor_id;
	String s_doctor_pw;
	TextView t_doctor_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_3_1_3_introduce_product);	
		Intent intent = getIntent();
		s_doctor_id = intent.getStringExtra("doctor_id");	
		s_doctor_pw = intent.getStringExtra("doctor_pw");
		t_doctor_id = (TextView)findViewById(R.id.user_id);
		t_doctor_id.setText(s_doctor_id + "��, �ݰ����ϴ�.");
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

	public void introduce_maker(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent introduce_maker = new Intent(this, CPU_3_1_1_Maker.class);
		introduce_maker.putExtra("doctor_id", s_doctor_id);
		introduce_maker.putExtra("doctor_pw", s_doctor_pw);
		startActivity(introduce_maker);
		this.onDestroy();
	}
	public void introduce_hello(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent introduce_hello = new Intent(this, CPU_3_1_2_Hello.class);
		introduce_hello.putExtra("doctor_id", s_doctor_id);
		introduce_hello.putExtra("doctor_pw", s_doctor_pw);
		startActivity(introduce_hello);
		this.onDestroy();
	}
	public void introduce_product2(View v){	
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);			
		Intent introduce_product2 = new Intent(this, CPU_3_1_3_IntroduceProduct.class);
		introduce_product2.putExtra("doctor_id", s_doctor_id);
		introduce_product2.putExtra("doctor_pw", s_doctor_pw);
		startActivity(introduce_product2);
		this.onDestroy();
	}
	public void introduce_howtouse(View v){		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent introduce_howtouse = new Intent(this, CPU_3_1_4_HowToUse.class);
		introduce_howtouse.putExtra("doctor_id", s_doctor_id);
		introduce_howtouse.putExtra("doctor_pw", s_doctor_pw);
		startActivity(introduce_howtouse);
		this.onDestroy();
	}

}
