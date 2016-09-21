package com.example.protector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Edit_Data extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.edit_data);	
		//info_드라마.xml의 내용이 보이도록 한다.
		setTitle("정보수정"); 
		//액티비티의 이름을 지정해준다.
	}

	protected void onDestroy(){		
		super.onDestroy();
		this.finish();
	}

	public void info(View v){		
		Intent it = new Intent(this, Data.class);
		startActivity(it);
	}

	public void edit(View v){		
		Intent it = new Intent(this, Edit_Data.class);
		startActivity(it);
	}


}
