package com.ak.wordcount.util;

import java.util.Arrays;
import java.util.List;

public class TextProcessingUtil {

	/**
	 * Splits the given text into words based on non-word characters.
	 * 
	 * @param text The input text to be split.
	 * @return List of words.
	 */
	public static List<String> splitTextIntoWords(String text) {
		return Arrays.asList(text.toLowerCase().split("\\W+"));
	}

	/**
	 * Checks if the Word contains non-alphabetic characters
	 * 
	 * @param word Input word to be checked.
	 * @return boolean true or false.
	 */
	public static boolean checkWordValidity(String word) {

		if (!word.matches("[a-zA-Z]+")) {
			return false;
		}
		return true;
	}
}
