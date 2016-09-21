package com.example.protector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;

public class CPU_1_2_0_Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_1_2_0_login);
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
	public void login_user(View v){		
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent login_user = new Intent(this, CPU_1_2_1_LoginUser.class);
		startActivity(login_user);
		this.onDestroy();
	}	
	public void login_doctor(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		Intent login_doctor = new Intent(this, CPU_1_2_2_LoginDoctor.class);
		startActivity(login_doctor);
		this.onDestroy();
	}
}
