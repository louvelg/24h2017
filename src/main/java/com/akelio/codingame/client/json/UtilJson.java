package com.akelio.codingame.client.json;

public class UtilJson {
	

	public static synchronized Object parseJson(String in) throws Exception {
		return new Parser2(false).parse(clean(in));
	}
	
	public static synchronized Object parseJsonD(String in) throws Exception {
		return new Parser2(true).parse(clean(in));
	}
	

	private static String clean(String in)
	{return in.trim().replace("\n","").replace("\t","").replace("\r","").replace("\b","").replace("\f","");}
}
