package com.example.protector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CPU_1_1_1_Search extends Activity{

	EditText e_product_num;
	String s_product_num;

	TextView t_check_pn;
	String s_check_pn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_1_1_1_search);	

		e_product_num = (EditText)findViewById(R.id.product_num);
		s_product_num = e_product_num.getText().toString();

	}


	protected void onDestroy(){		
		super.onDestroy();
		this.finish();
	}

	public void show_data(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		

		String s_product_num = e_product_num.getText().toString();

		if(s_product_num.equals("")){
			Toast toast = Toast.makeText(this, "제품번호를 입력해주세요~", Toast.LENGTH_SHORT);
			//원하는 텍스트 메세지가 뜨도록 Toast 변수를 선언하고 문자열을 지정한다.
			toast.show();
			//toast가 보여지게 한다.
		}
		else{
			new Socket_Java();
			Intent show_data = new Intent(this, CPU_1_1_2_ShowData.class);
			show_data.putExtra("product_num", s_product_num);
			startActivity(show_data);
			this.onDestroy();					
		}

	}

	public void main(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);		
		Intent main = new Intent(this, ActivityMain.class);
		startActivity(main);
		this.onDestroy();
	}
}
