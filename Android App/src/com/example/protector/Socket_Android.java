package com.example.protector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Socket_Android extends Activity {
	private Socket socket;
	BufferedReader socket_in;
	PrintWriter socket_out;
	EditText input;
	Button button;
	TextView output;
	String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket_android);
        
        input = (EditText)findViewById(R.id.input);
        button = (Button)findViewById(R.id.button);
        output = (TextView) findViewById(R.id.output);
        button.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		String data = input.getText().toString();
        		Log.w("newwork", " "+data);
        		if(data != null) {
        			socket_out.println(data);
        			//Toast.makeText(getApplicationContext(),"버튼눌림", Toast.LENGTH_SHORT).show();
        		}
    				
        	}
        });
        
        Thread worker = new Thread() {
        	public void run() {
        		try {
        			socket = new Socket("192.168.43.245", 9000);
        			socket_out = new PrintWriter(socket.getOutputStream(), true);
        			socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        		
        		try {       			        			
        			while(true) {
        				data =socket_in.readLine();
        				output.post(new Runnable() {
        					public void run() {
        						output.setText(data);
        						Toast.makeText(getApplicationContext(),"버튼눌림", Toast.LENGTH_SHORT).show();
        					}
        				});
        			}
        		} catch (Exception e) {  			
        		}
        	}
        };
        worker.start();
    }


    @Override
    protected void onStop() {
    	super.onStop();
    	try {
    		socket.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }    
}
