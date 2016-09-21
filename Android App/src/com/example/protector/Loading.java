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
				//Loadingȭ�� �� ������ activity�� MainActivity.java�� �����Ѵ�.
				Loading.this.finish();			
				//MainActivity�� ����Ǹ� Loading�� �����Ѵ�.
			}
		},2000);

		Toast toast = Toast.makeText(this, "LOADING����������\n ��ø� ��ٷ��ּ���~", Toast.LENGTH_SHORT);
		//���ϴ� �ؽ�Ʈ �޼����� �ߵ��� Toast ������ �����ϰ� ���ڿ��� �����Ѵ�.
		toast.show();
		//toast�� �������� �Ѵ�.
	}
}
