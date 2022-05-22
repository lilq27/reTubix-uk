package com.reTubix.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FindInfoUtil {

	public String findInfo(String stringURL) throws IOException {
		
		HttpURLConnection con = null;
		StringBuffer stringBuffer = null;
		URL url = new URL(stringURL);
		
		try {
			con = (HttpURLConnection)url.openConnection();
			stringBuffer = new StringBuffer();
			
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            String str ;
            while((str=br.readLine()) != null){
                 
            	stringBuffer.append(str + "\r\n") ;
            }
             
            System.out.println(stringBuffer.toString()) ;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stringBuffer.toString();
	}
}
