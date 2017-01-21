package com.akelio.codingame.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Util {

	public static Map<String,String> argsToParams(String[] args) {
		Map<String,String> m = new HashMap<>();
		
		for(String arg:args) {
			String[] k = arg.split("=",2);
			if(k.length!=2) throw new RuntimeException("Invalid arg value: "+arg);
			m.put(k[0], k[1]);
		}
		
		return m;
	}
	
	public static String getValue(Map<String,String> m, String key, String defaultValue) {
		if(!m.containsKey(key)) return defaultValue;
		return m.get(key);
	}
	
	
	public static String retrieveString(URLConnection con) throws IOException {
			
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			StringBuffer buffer = new StringBuffer();
			
			int b;
			while((b = isr.read())!=-1)
			{buffer.append((char)b);}
			
			isr.close();
			if(con instanceof HttpURLConnection)
				((HttpURLConnection)con).disconnect();
				
			return buffer.toString();
	}
}
