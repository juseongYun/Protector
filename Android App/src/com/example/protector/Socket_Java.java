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
       // ���������� �����ϰ� 5000�� ��Ʈ�� ����(bind) ��Ų��.
       serverSocket = new ServerSocket(9000);
       System.out.println(getTime() + " ������ �غ�Ǿ����ϴ�.");
   } catch (IOException e) {
       e.printStackTrace();
   } // try - catch
   
   while (true) {
       try {
           System.out.println(getTime() + " �����û�� ��ٸ��ϴ�.");
           // ���������� Ŭ���̾�Ʈ�� �����û�� �� ������ ������ ���߰� ��� ��ٸ���.
           // Ŭ���̾�Ʈ�� �����û�� ���� Ŭ���̾�Ʈ ���ϰ� ����� ���ο� ������ �����Ѵ�.
           Socket socket = serverSocket.accept();
           System.out.println(getTime() + socket.getInetAddress() + " �κ��� �����û�� ���Խ��ϴ�.");
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));           
            
            while(true)
            {
            	String st=scan.nextLine();
            System.out.println(st);
            out.println(st);
            if(st.equals("quit"))break;
            }
           // ���� ����(remote socket)�� �����͸� ������.
           
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