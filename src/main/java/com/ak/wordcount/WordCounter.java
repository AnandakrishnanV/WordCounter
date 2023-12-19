package com.ak.wordcount;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.ak.wordcount.interfaces.IWordCounter;
import com.ak.wordcount.interfaces.Translator;
import com.ak.wordcount.util.TextProcessingUtil;
import com.ak.wordcount.util.WordCountUtil;

@Service
public class WordCounter implements IWordCounter {

	private final Translator translator;
	private final ConcurrentHashMap<String, AtomicInteger> wordCounts;
	
	private static final Logger LOGGER = Logger.getLogger(WordCounter.class.getName());

	public WordCounter(Translator translator) {
		this.translator = translator;
		this.wordCounts = new ConcurrentHashMap<>();
	}

	/**
	 * Adds words from a given text to the word counter.
	 * 
	 * @param text The input text containing words to be counted.
	 */

	@Override
	public void addWords(String text) {
		if (text == null || text.isEmpty()) {
			LOGGER.warning("Empty or null text provided");
	        return;
	    }
		List<String> words = TextProcessingUtil.splitTextIntoWords(text);
		WordCountUtil.processWordsInParallel(words, wordCounts, translator);
	}

	/**
	 * Retrieves the count of a specific word.
	 *
	 * @param word The word whose count is to be retrieved.
	 * @return The count of the word.
	 */

	@Override
	public int getWordCount(String word) {
		if (!TextProcessingUtil.checkWordValidity(word)) {
			LOGGER.warning("Invalid word provided");
			return 0; // Return 0 for invalid words
		}
		String translatedWord = translator.translate(word.toLowerCase());
		return wordCounts.getOrDefault(translatedWord, new AtomicInteger(0)).get();
	}

}