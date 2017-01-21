package com.akelio.codingame.client.json;

public class UtilJson {
	
	private static Parser2 parser = new Parser2(false);
	private static Parser2 parserD = new Parser2(true);
	

	public static Object parseJson(String in) throws Exception {
		return parser.parse(clean(in));
	}
	
	public static Object parseJsonD(String in) throws Exception {
		return parserD.parse(clean(in));
	}
	

	private static String clean(String in)
	{return in.trim().replace("\n","").replace("\t","").replace("\r","").replace("\b","").replace("\f","");}
}
