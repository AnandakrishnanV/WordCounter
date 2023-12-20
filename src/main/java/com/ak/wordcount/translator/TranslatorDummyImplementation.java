package com.ak.wordcount.translator;

import org.springframework.stereotype.Service;

import com.ak.wordcount.interfaces.Translator;

/** Dummy implementation to make the micro-service functional. Must be replaced with actual Translator
*/

@Service
public class TranslatorDummyImplementation implements Translator{

	/**
	 * Dummy function. Returns the input word as is
	 * 
	 * @param text The input text containing words to be counted.
	 * @result word returns the same word back (Should be translated word in actual implementation)
	 */
	
	@Override
	public String translate(String word) {
		return word;
	}

}
