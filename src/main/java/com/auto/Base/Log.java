package com.auto.Base;

public class Log {
	public static void pass(String description) {
		System.out.println("+PASS: " + description);
	}

	public static void error(String description) {
		System.out.println("+ERROR: " + description);
	}

	public static void fail(String description) {
		System.out.println("+FAIL: " + description);
	}

	public static void info(String description) {
		System.out.println("+INFO: " + description);
	}
}
