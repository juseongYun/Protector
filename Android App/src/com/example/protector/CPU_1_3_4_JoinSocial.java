package com.example.protector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;

public class CPU_1_3_4_JoinSocial extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_1_3_1_join);
		//activity_main.xml이 보여지도록 한다.
	}
	protected void onDestroy(){		
		super.onDestroy();
		this.finish();
	}	
	public void join_user(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent join_user = new Intent(this, CPU_1_1_1_Search.class);
		startActivity(join_user);
		this.onDestroy();
	}	
	public void join_doctor(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent join_doctor = new Intent(this, CPU_1_2_1_LoginUser.class);
		startActivity(join_doctor);
		this.onDestroy();
	}
}
