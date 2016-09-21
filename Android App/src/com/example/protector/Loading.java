package com.example.protector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

public class Loading extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loading);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable(){
			public void run(){
				startActivity(
						new Intent(getApplication(), ActivityMain.class));
				//Loading화면 후 실행할 activity를 MainActivity.java로 지정한다.
				Loading.this.finish();			
				//MainActivity가 실행되면 Loading은 종료한다.
			}
		},2000);

		Toast toast = Toast.makeText(this, "LOADING·····\n 잠시만 기다려주세요~", Toast.LENGTH_SHORT);
		//원하는 텍스트 메세지가 뜨도록 Toast 변수를 선언하고 문자열을 지정한다.
		toast.show();
		//toast가 보여지게 한다.
	}
}
