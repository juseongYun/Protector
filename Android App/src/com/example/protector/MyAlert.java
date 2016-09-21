package com.example.protector;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MyAlert extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_main);
    /*requestWindowFeature(Window.FEATURE_NO_TITLE);      
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, 
            WindowManager.LayoutParams.FLAG_BLUR_BEHIND);*/

    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

    // set title
    
    TextView title = new TextView(MyAlert.this);
    title.setText("★PROTECTOR 알림★");
    title.setTextColor(Color.RED);
    title.setTextSize(20);
    title.setGravity(Gravity.CENTER);
    alertDialogBuilder.setCustomTitle(title);           
    TextView message = new TextView(MyAlert.this);
    message.setText("응급상황을 알립니다!!!!!!");
    message.setTextColor(Color.RED);
    message.setTextSize(30);
    message.setGravity(Gravity.CENTER);
    alertDialogBuilder.setView(message);  
    // set dialog message
    alertDialogBuilder
            .setCancelable(false)
            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, just close
                    // the dialog box and do nothing
                    //stopService(getIntent());
                    dialog.cancel();
                    finish();
                }
            });

    // create alert dialog
    AlertDialog alertDialog = alertDialogBuilder.create();

    // show it
    alertDialog.show();
}}