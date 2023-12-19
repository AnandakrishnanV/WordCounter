package com.ak.wordcount;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import com.ak.wordcount.interfaces.Translator;
import com.ak.wordcount.models.TrieNode;
import com.ak.wordcount.util.CheckWordValidity;

public class WordCounterTrie {

	private final Translator translator;
	private final TrieNode root;
	
	private final int batchSize = 10000; // Must be tuned depending on usecase

	public WordCounterTrie(Translator translator) {
		this.translator = translator;
		this.root = new TrieNode();
	}

	public void addWord(String text) { // Assumes input of paragraph(s) of text

		List<String> words = Arrays.asList(text.toLowerCase().split("\\W+"));
		ForkJoinPool customPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

		try {
			customPool.submit(() ->
            words.parallelStream().forEach(word -> {
                try {
                    if (!CheckWordValidity.check(word)) {
                        throw new IllegalArgumentException("Word contains non-alphabetic characters: " + word);
                    }
                    String translatedWord = translator.translate(word);
                    insertWord(translatedWord);
                } catch (IllegalArgumentException e) {
                    // Log the exception and continue with other words
                    System.err.println(e.getMessage());
                }
            })
        ).get(); // Blocks until all tasks are completed
		} catch (InterruptedException | ExecutionException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Error processing words in parallel", e);
		} finally {
			customPool.shutdown(); // Important to prevent resource leakage
		}
	}

	private void insertWord(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        current.endOfWordCount.incrementAndGet();
    }

    public int getWordCount(String word) {			
    	
    	if (!CheckWordValidity.check(word)) {
            return 0;
        }
    	
        TrieNode current = root;
        String translatedWord = translator.translate(word.toLowerCase());

        for (char ch : translatedWord.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) {
                return 0;
            }
        }

        return current.endOfWordCount.get();
    }

}