package com.example.protector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class CopyOfActivityMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);
		//activity_main.xml�� ���������� �Ѵ�.
	}
	protected void onDestroy(){		
		super.onDestroy();
		this.finish();
	}	
	public void data(View v){		
		Intent it = new Intent(this, CPU_1_1_1_Search.class);
		startActivity(it);
	}	
	public void login(View v){		
		Intent it = new Intent(this, CPU_1_2_1_LoginUser.class);
		startActivity(it);
	}
	public void join(View v){		
		Intent it = new Intent(this, CPU_1_3_1_Join.class);
		startActivity(it);
	}
	   
}
