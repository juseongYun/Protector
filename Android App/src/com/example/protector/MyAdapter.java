package com.example.protector;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<MyData> arrData;
	private LayoutInflater inflater;

	public MyAdapter(Context c, ArrayList<MyData> arr) {
		this.context = c;
		this.arrData = arr;
		inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return arrData.size();
	}

	public Object getItem(int position) {
		return arrData.get(position).getName();
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			convertView = inflater.inflate(R.layout.patient_list, parent, false);
		}

		TextView name = (TextView)convertView.findViewById(R.id.name);
		name.setText(arrData.get(position).getName());

		TextView tel = (TextView)convertView.findViewById(R.id.sex);
		tel.setText(arrData.get(position).getSex());

		TextView date = (TextView)convertView.findViewById(R.id.date);
		date.setText(arrData.get(position).getDate());

		final TextView pronum = (TextView)convertView.findViewById(R.id.pronum);
		pronum.setText(arrData.get(position).getPronum());


		Button modifyBtn = (Button)convertView.findViewById(R.id.modifybtn);

		modifyBtn.setOnClickListener(new OnClickListener() {   
			public void onClick(View v) {
				Toast.makeText(context, "조회합니다.", Toast.LENGTH_SHORT).show();
				String s_patient_product_num = pronum.getText().toString();
				Intent data_patient = new Intent();
				data_patient.putExtra("patient_product_num", s_patient_product_num);
				startActivity(data_patient);
			}

		});

		Button delBtn = (Button)convertView.findViewById(R.id.delbtn);
		delBtn.setOnClickListener(new OnClickListener() {   
			public void onClick(View v) {
				AlertDialog.Builder dlg = new AlertDialog.Builder(context);
				dlg.setMessage("리스트에서 삭제 하시겠습니까?");
				dlg.setTitle("안내");
				dlg.setPositiveButton("확인",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which){

					}
				});
				dlg.setNegativeButton("취소",null);
				dlg.show();
			}
		});

		return convertView;
	}

	protected Context getActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	protected Context getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void startActivity(Intent data_patient) {
		// TODO Auto-generated method stub

	}

	protected Vibrator getSystemService(String vibratorService) {
		// TODO Auto-generated method stub
		return null;
	}

}


