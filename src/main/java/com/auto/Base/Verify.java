package com.auto.Base;

public class Verify {

	public static void verifyEquals(Object expected, Object result, String description) {
		if (expected.equals(result)) {
			Log.pass(description);
		} else {
			Log.error(description);
		}
	}

	public static void verifyTrue(boolean isTrue, String description) {
		if (isTrue) {
			Log.pass(description);
		} else {
			Log.error(description);
		}
	}
}
