package com.ak.wordcount.util;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import com.ak.wordcount.WordCounter;
import com.ak.wordcount.interfaces.Translator;

public class WordCountUtil {
	
	private static final Logger LOGGER = Logger.getLogger(WordCountUtil.class.getName());

	/**
	 * Processes a single word: validates, translates, and updates its count.
	 * 
	 * @param word       The word to be processed.
	 * @param wordCounts The map of word counts.
	 * @param translator The translator interface.
	 */
	public static void processWord(String word, ConcurrentHashMap<String, AtomicInteger> wordCounts,
			Translator translator) {
		if (!TextProcessingUtil.checkWordValidity(word)) {
			return; // Skip invalid words
		}
		String translatedWord = translator.translate(word);
		updateWordCount(translatedWord, wordCounts);
	}

	/**
	 * Processes a list of words in parallel, updating their counts.
	 * 
	 * @param words      The list of words to be processed.
	 * @param wordCounts
	 * @param translator
	 */
	public static void processWordsInParallel(List<String> words, ConcurrentHashMap<String, AtomicInteger> wordCounts,
			Translator translator) {
		ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		try {
			customThreadPool
					.submit(() -> words.parallelStream().forEach(word -> processWord(word, wordCounts, translator)))
					.get(); // Ensures completion of all tasks
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.severe("Error in parallel processing: " + e.getMessage());
			Thread.currentThread().interrupt();
			throw new RuntimeException("Error processing words in parallel", e);
		} finally {
			customThreadPool.shutdown(); // Prevents resource leakage
		}
	}

	/**
	 * Updates the count of a given word in the word counter.
	 * 
	 * @param word       The word whose count is to be updated.
	 * @param wordCounts The map of word counts.
	 */
	public static void updateWordCount(String word, ConcurrentHashMap<String, AtomicInteger> wordCounts) {
		wordCounts.compute(word, (key, count) -> {
			if (count == null)
				return new AtomicInteger(1);
			count.incrementAndGet();
			return count;
		});
	}

}
