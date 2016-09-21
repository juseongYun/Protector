package com.example.protector;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Socket_Java {

   /**
    * @param args
    */
   @SuppressWarnings("resource")
public static void main(String[] args) {
      ServerSocket serverSocket = null;
    Scanner scan=new Scanner(System.in);
   try {
       // 서버소켓을 생성하고 5000번 포트와 결합(bind) 시킨다.
       serverSocket = new ServerSocket(9000);
       System.out.println(getTime() + " 서버가 준비되었습니다.");
   } catch (IOException e) {
       e.printStackTrace();
   } // try - catch
   
   while (true) {
       try {
           System.out.println(getTime() + " 연결요청을 기다립니다.");
           // 서버소켓은 클라이언트의 연결요청이 올 때까지 실행을 멈추고 계속 기다린다.
           // 클라이언트의 연결요청이 오면 클라이언트 소켓과 통신할 새로운 소켓을 생성한다.
           Socket socket = serverSocket.accept();
           System.out.println(getTime() + socket.getInetAddress() + " 로부터 연결요청이 들어왔습니다.");
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));           
            
            while(true)
            {
            	String st=scan.nextLine();
            System.out.println(st);
            out.println(st);
            if(st.equals("quit"))break;
            }
           // 원격 소켓(remote socket)에 데이터를 보낸다.
           
       } catch (IOException e) {
           e.printStackTrace();
       } // try - catch
   } // while

   }

    static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date());
    } // getTime


}