package com.example.protector;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class CPU_1_2_2_LoginDoctor extends Activity  {

	EditText e_doctor_id;
	EditText e_doctor_pw;

	String s_get_doctor_id;
	String s_get_doctor_pw;

	String d_encoding_pw;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_1_2_2_logindoctor);	
		e_doctor_id = (EditText)findViewById(R.id.user_id);
		e_doctor_pw = (EditText)findViewById(R.id.user_pw);

		s_get_doctor_id = e_doctor_id.getText().toString();
		s_get_doctor_pw = e_doctor_pw.getText().toString();
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

	public void mypage_doctor(View v){				
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);
		String s_doctor_id = e_doctor_id.getText().toString();
		String s_doctor_pw = e_doctor_pw.getText().toString();

		try {
			d_encoding_pw = toSHA1(s_doctor_pw);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		if(s_doctor_id.equals("")){
			Toast toast = Toast.makeText(this, "아이디를 입력해주세요~", Toast.LENGTH_SHORT);
			//원하는 텍스트 메세지가 뜨도록 Toast 변수를 선언하고 문자열을 지정한다.
			toast.show();
			//toast가 보여지게 한다.
		}
		else if(s_doctor_pw.equals("")){
			Toast toast = Toast.makeText(this, "비밀번호를 입력해주세요~", Toast.LENGTH_SHORT);
			//원하는 텍스트 메세지가 뜨도록 Toast 변수를 선언하고 문자열을 지정한다.
			toast.show();
			//toast가 보여지게 한다.
		}
		else{
			Intent mypage = new Intent(this, CPU_3_2_0_Patient_List.class);
			mypage.putExtra("doctor_id", s_doctor_id);
			mypage.putExtra("doctor_pw", d_encoding_pw);
			startActivity(mypage);
			this.onDestroy();
		}

	}

	//암호화
	public static String toSHA1(String s) throws UnsupportedEncodingException {
		try {
			// Create MD5 Hash
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(s.getBytes("UTF-8"));
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++)
				hexString.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}



}
