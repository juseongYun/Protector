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

public class CPU_1_2_1_LoginUser extends Activity  {

	EditText e_user_id;
	EditText e_user_pw;

	String s_get_user_id;
	String s_get_user_pw;

	String encoding_pw;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.cpu_1_2_1_loginuser);	
		e_user_id = (EditText)findViewById(R.id.user_id);
		e_user_pw = (EditText)findViewById(R.id.user_pw);

		s_get_user_id = e_user_id.getText().toString();
		s_get_user_pw = e_user_pw.getText().toString();
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

	public void mypage_user(View v){			
		Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibe_add.vibrate(50);	
		String s_user_id = e_user_id.getText().toString();
		String s_user_pw = e_user_pw.getText().toString();

		try {
			encoding_pw = toSHA1(s_user_pw);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		if(s_user_id.equals("")){
			Toast toast = Toast.makeText(this, "���̵� �Է����ּ���~", Toast.LENGTH_SHORT);
			//���ϴ� �ؽ�Ʈ �޼����� �ߵ��� Toast ������ �����ϰ� ���ڿ��� �����Ѵ�.
			toast.show();
			//toast�� �������� �Ѵ�.
		}
		else if(s_user_pw.equals("")){
			Toast toast = Toast.makeText(this, "��й�ȣ�� �Է����ּ���~", Toast.LENGTH_SHORT);
			//���ϴ� �ؽ�Ʈ �޼����� �ߵ��� Toast ������ �����ϰ� ���ڿ��� �����Ѵ�.
			toast.show();
			//toast�� �������� �Ѵ�.
		}
		else{
			Intent mypage = new Intent(this, CPU_2_2_1_Data.class);
			mypage.putExtra("user_id", s_user_id);
			mypage.putExtra("user_pw", encoding_pw);
			startActivity(mypage);
			this.onDestroy();
		}

	}

	//��ȣȭ
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
