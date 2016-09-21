package com.example.protector;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class ImageAdapter extends BaseAdapter {
	private final ImageDownloader imageDownloader = new ImageDownloader();
	private String[] strURLList;
	private Context context;

	
	public ImageAdapter(String _strURLList[], Context c)
	{
		context=c;
		if (_strURLList != null) 
		{
			strURLList = _strURLList;
		}
	}
	

	public int getCount() 
    {
        return strURLList.length;
    }

    public String getItem(int position) 
    {
        return strURLList[position];
    }

    public long getItemId(int position) 
    {
        return strURLList[position].hashCode();
    }

    public View getView(int position, View view, ViewGroup parent) 
    {
    	ImageView imageView;
        if (view == null) 
        {
        	imageView = new ImageView(parent.getContext());
        	imageView.setLayoutParams(new GridView.LayoutParams(300, 300)); //85,85
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(10, 5, 5, 50);
        	imageDownloader.download(strURLList[position], (ImageView)imageView);
            final int pos = position;
            
            imageView.setOnClickListener(new View.OnClickListener(){
            	 public void onClick(View v){
            		View dialogView =(View)View.inflate(context,R.layout.cpu_2_2_3_photo2,null);
            		AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            		ImageView picture = (ImageView)dialogView.findViewById(R.id.imageView1);
            		 imageDownloader.download(strURLList[pos], (ImageView)picture);
            		 dlg.setView(dialogView);
            		 dlg.setNegativeButton("�ݱ�",null);
            		 dlg.show();
    	        	//Intent intent = new Intent(this, FullImage.this);// �����Ѿ ȭ��
    	        	//intent.putExtra("image", BITMAP);
                //startActivity(intent);
            }
        });
        }
        else
        {
        	imageView = (ImageView) view;
        }

		

        return imageView;
    }

    public ImageDownloader getImageDownloader() 
    {
        return imageDownloader;
    }
}
