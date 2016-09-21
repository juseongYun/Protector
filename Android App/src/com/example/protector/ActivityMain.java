package com.example.protector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;

public class ActivityMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);
		//activity_main.xml이 보여지도록 한다.
	}

	protected void onDestroy(){		
		super.onDestroy();
		this.finish();
	}	

	public void data(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(100);	
		Intent it = new Intent(this, CPU_1_1_1_Search.class);
		startActivity(it);
	}	

	public void login(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(100);	
		Intent it = new Intent(this, CPU_1_2_0_Login.class);
		startActivity(it);
	}

	public void join(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(100);	
		Intent it = new Intent(this, CPU_1_3_1_Join.class);
		startActivity(it);
	}

}
