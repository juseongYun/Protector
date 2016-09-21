package com.example.protector;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.handstudio.android.hzgrapherlib.animation.GraphAnimation;
import com.handstudio.android.hzgrapherlib.graphview.CurveGraphView;
import com.handstudio.android.hzgrapherlib.vo.GraphNameBox;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraph;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraphVO;

public class CPU_1_1_3_ShowGraph extends Activity {

   String s_product_num;
   TextView t_product_num;

   private ViewGroup layoutGraphView;
   
   int M=0;
   
   String[] word;
   String[] time;
   float[] temper;
   float[] perse;
//   String word[]= new String[M*3+1];
//   String time[]=new String[M];
//   float temper[]=new float[M];
//   float perse[]=new float[M];
   int i=0,j;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      requestWindowFeature(Window.FEATURE_NO_TITLE);

      setContentView(com.example.protector.R.layout.cpu_1_1_3_showgraph);   
      Intent intent = getIntent();
      s_product_num = intent.getStringExtra("product_num");

      t_product_num = (TextView)findViewById(com.example.protector.R.id.product_num);
      t_product_num.setText(s_product_num + "님, 반갑습니다.");

      layoutGraphView = (ViewGroup)findViewById(R.id.layoutGraphView);
      String strUrl = "http://220.67.113.124/CPU/mobile/graph.php?product_num="+s_product_num;
      new DownloadWebpageTask().execute(strUrl);
   }

   private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

      @Override
      protected String doInBackground(String... urls) {
         try{
            return (String)downloadUrl((String)urls[0]);
         } catch (IOException e) {
            return "error";
         }
      }

      protected void onPostExecute(String result) {          

         
         StringTokenizer s = new StringTokenizer(result);
         
//         String M2= s.nextToken("totalrecord");
//         int m = Integer.parseInt(M2);
//         M=Integer.parseInt(M2);
         
               
         String word2="";
         int pt_start=-1;
         int pt_end=-1;
         int i;
         String tag_start = "totalrecord=";
         String tag_end = "=totalrecord";

         int mm=0;

            pt_start= result.indexOf("totalrecord=");
            if(pt_start != -1) {
               pt_end = result.indexOf("=totalrecord");
               if(pt_end != -1) {
                  word2 = result.substring(pt_start + tag_start.length(), pt_end);
                   mm=Integer.parseInt(word2);
               }     
            }

            M=mm;

            word = new String[M*3+1];
            time = new String[M];
            temper = new float[M];
            perse = new float[M];
         
         for(i=0; i<M*3+1; i++){
            word[i]=s.nextToken("^");
         }

         j=0;
         for(i=1; i<M*3+1; i=i+3){
            time[j]=word[i];
            j++;
         }

         j=0;
         for(i=2; i<M*3+1; i=i+3){
            temper[j]=Float.valueOf(word[i]);
            j++;
         }

         j=0;
         for(i=3; i<M*3+1; i=i+3){
            perse[j]=Float.valueOf(word[i]);;
            j++;
         }

         setCurveGraph();

      }

      private String downloadUrl(String myurl) throws IOException {
         HttpURLConnection conn = null;
         try {
            URL url = new URL(myurl);
            conn = (HttpURLConnection)url.openConnection();
            BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
            String line = null;
            String page = "";
            while((line = bufreader.readLine()) != null) {
               page += line;
            }

            return page;
         } finally {
            conn.disconnect();
         }
      }
   }


   private void setCurveGraph() {
      //all setting
      CurveGraphVO vo = makeCurveGraphAllSetting();

      layoutGraphView.addView(new CurveGraphView(this, vo));
   }


   /**
    * make Curve graph using options
    * @return
    */
   private CurveGraphVO makeCurveGraphAllSetting() {

      int i;
      //BASIC LAYOUT SETTING
      //padding
      int paddingBottom    = 100;
      int paddingTop       = 70;
      int paddingLeft    = 150;
      int paddingRight    = 0;

      //graph margin
      int marginTop       = CurveGraphVO.DEFAULT_MARGIN_TOP;
      int marginRight    = 50;

      //max value
      int maxValue       = 100;

      //increment
      int increment       = 10;

      //GRAPH SETTING
      String[] legendArr    = new String[M];
      float[] graph1 = new float[M];
      float[] graph2 = new float[M];
      for(i=0; i<M; i++) {
         legendArr[i]=time[i];
      }
      for(i=0; i<M; i++) {
         graph1[i]=temper[i];
      }
      for(i=0; i<M; i++) {
         graph2[i]=perse[i];
      }

      List<CurveGraph> arrGraph       = new ArrayList<CurveGraph>();

      arrGraph.add(new CurveGraph("심박수", 0xaa00ffff, graph2));
      arrGraph.add(new CurveGraph("체온", 0xaa66ff33, graph1));      


      CurveGraphVO vo = new CurveGraphVO(
            paddingBottom, paddingTop, paddingLeft, paddingRight,
            marginTop, marginRight, maxValue, increment, legendArr, arrGraph);

      //set animation
      vo.setAnimation(new GraphAnimation(GraphAnimation.LINEAR_ANIMATION, GraphAnimation.DEFAULT_DURATION));
      //set graph name box
      vo.setGraphNameBox(new GraphNameBox());
      //set draw graph region
      //vo.setDrawRegion(true);

      //use icon
      //      arrGraph.add(new Graph(0xaa66ff33, graph1, R.drawable.icon1));
      //      arrGraph.add(new Graph(0xaa00ffff, graph2, R.drawable.icon2));

      //      CurveGraphVO vo = new CurveGraphVO(
      //            paddingBottom, paddingTop, paddingLeft, paddingRight,
      //            marginTop, marginRight, maxValue, increment, legendArr, arrGraph, R.drawable.bg);
      return vo;
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

   public void show_medicaldata(View v){      
      Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
      vibe_add.vibrate(50);      
      Intent show_medicaldata = new Intent(this, CPU_1_1_2_ShowData.class);
      show_medicaldata.putExtra("product_num", s_product_num);
      startActivity(show_medicaldata);
      this.onDestroy();
   }
   public void show_photo(View v){            
      Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
      vibe_add.vibrate(50);
      Intent show_photo = new Intent(this, CPU_1_1_4_ShowPhoto.class);
      show_photo.putExtra("product_num", s_product_num);
      startActivity(show_photo);
      this.onDestroy();
   }
   public void show_alram(View v){         
      Vibrator vibe_add = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
      vibe_add.vibrate(50);   
      Intent show_alram = new Intent(this, CPU_1_1_5_ShowAlram.class);
      show_alram.putExtra("product_num", s_product_num);
      startActivity(show_alram);
      this.onDestroy();
   }

}