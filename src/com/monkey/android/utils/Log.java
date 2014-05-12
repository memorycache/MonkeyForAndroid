package com.monkey.android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	public static final String ERROR = "error";
	public static final String DEBUG = "debug";

	public static void print(String log, String tag) {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String time = format.format(now);
		String logs = "[" + time + "]" + "[" + tag + "]" + log;
		System.out.println(logs);
	}

	public static void print(Exception e, String tag) {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String time = format.format(now);
		String logs = "[" + time + "]" + "[" + tag + "]" + "error message:";
		System.out.println(logs);
		StackTraceElement[] messages = e.getStackTrace();
		for (int i = 0; i < messages.length; i++) {
			System.out.println(messages[i].toString());
		}
	}
}
