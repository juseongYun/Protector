package com.example.protector;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;



public class List_Adapter extends BaseAdapter{
	private LayoutInflater mInflater;
	private Activity m_activity;
	private ArrayList<CPU_2_3_1_FAQ2> arr;
	private Context context;
	public List_Adapter(Activity act, ArrayList<CPU_2_3_1_FAQ2> arr_item, Context c) {
		context=c;
		this.m_activity = act;
		arr = arr_item;
		mInflater = (LayoutInflater)m_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return arr.size();
	}
	@Override
	public Object getItem(int position) {
		return arr.get(position);
	}
	public long getItemId(int position){
		return position;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			int res = 0;
			res = com.example.protector.R.layout.list_item;
			convertView = mInflater.inflate(res, parent, false);
		}
		TextView number = (TextView)convertView.findViewById(R.id.number);
		TextView subject = (TextView)convertView.findViewById(R.id.subject);
		TextView name = (TextView)convertView.findViewById(R.id.name);
		TextView date = (TextView)convertView.findViewById(R.id.date);
		//TextView content = (TextView)convertView.findViewById(R.id.content);
		LinearLayout layout_view =  (LinearLayout)convertView.findViewById(R.id.vi_view);
		//int resId = m_activity.getResources().getIdentifier(arr.get(position).Picture, "drawable", m_activity.getPackageName());
		//imView.setBackgroundResource(resId);
		number.setText(arr.get(position).Number);
		subject.setText(arr.get(position).Subject);
		name.setText(arr.get(position).Name);
		date.setText(arr.get(position).Date);
		//content.setText(arr.get(position).Content);
		/*	버튼에 이벤트처리를 하기위해선 setTag를 이용해서 사용할 수 있습니다.
		 * 
		 * 	Button btn 가 있다면, btn.setTag(position)을 활용해서 각 버튼들의 이벤트처리를 할 수 있습니다.
		 */
		layout_view.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				AlertDialog.Builder dlg = new AlertDialog.Builder(context);
				dlg.setMessage("내용 : "+arr.get(position).Content);
				dlg.setTitle("제목 : "+arr.get(position).Subject);
				dlg.setNegativeButton("확인",null);
				dlg.show();

			}
		});
		return convertView;
	}
	public void GoIntent(int a){
		Intent intent = new Intent(m_activity, CPU_2_3_1_FAQ2.class);
		//putExtra 로 선택한 아이템의 정보를 인텐트로 넘겨 줄 수 있다.
		intent.putExtra("TITLE", arr.get(a).Number);
		intent.putExtra("EXPLAIN", arr.get(a).Subject);
		intent.putExtra("NAME", arr.get(a).Name);
		intent.putExtra("DATE", arr.get(a).Date);
		intent.putExtra("CONTENT", arr.get(a).Content);
		m_activity.startActivity(intent);
	}
}
