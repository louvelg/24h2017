package com.akelio.codingame.util;

public class UtilThread {

	public static void sleep(long lapse) {
		try {
			Thread.sleep(lapse);
		} catch (Exception e) {}
	}
}
