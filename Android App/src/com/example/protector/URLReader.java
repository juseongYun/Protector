package com.example.protector;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class URLReader {
	public static void main(String[] args) throws Exception {
		String m="Å×½ºÆ®123";
		String en = toSHA1(m);
		StringBuilder ar=new StringBuilder();
		ar.append(URLEncoder.encode("m","UTF-8"));
		ar.append('=');
		ar.append(URLEncoder.encode(m,"UTF-8"));
		ar.append("&");
		ar.append(URLEncoder.encode("en","UTF-8"));
		ar.append('=');
		ar.append(URLEncoder.encode(en,"UTF-8"));
		//byte[]bar=ar.toString().getBytes();     
		String ur = "http://220.67.113.124/test.php";
		ur+="?";
		ur+=ar;
		URL url = new URL(ur);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		/*conn.setRequestMethod("POST");*/
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
		conn.setRequestProperty("CHARSET","UTF-8");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		//conn.getOutputStream().write(bar);
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
	}
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