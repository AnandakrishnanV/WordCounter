package com.ak.wordcount.util;

public class CheckWordValidity {

	public static boolean check(String word) {

		if (!word.matches("[a-zA-Z]+")) {
			return false;
		}
		return true;
	}

}
