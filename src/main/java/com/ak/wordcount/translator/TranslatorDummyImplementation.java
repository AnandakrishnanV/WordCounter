package com.ak.wordcount.translator;

import org.springframework.stereotype.Service;

import com.ak.wordcount.interfaces.Translator;

@Service
public class TranslatorDummyImplementation implements Translator{

	/**
	 * Adding default implementation to make the micro-service functional. Must be replaced with actual Translator
	 * 
	 * @param text The input text containing words to be counted.
	 * @result word returns the same word back (Should be translated word in actual implementation)
	 */
	
	@Override
	public String translate(String word) {
		return word;
	}

}
