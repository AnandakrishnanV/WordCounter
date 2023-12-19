package com.ak.wordcount;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.ak.wordcount.interfaces.Translator;

public class WordCounter {
	
	private final Translator translator;
    private final ConcurrentHashMap<String, AtomicInteger> wordCounts;

    public WordCounter(Translator translator) {
        this.translator = translator;
        this.wordCounts = new ConcurrentHashMap<>();
    }
	
    public void addWord(String... words) {
    	for (String word : words) {
            if (!word.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Word contains non-alphabetic characters: " + word);
            }

            String translatedWord = translator.translate(word.toLowerCase());
            wordCounts.computeIfAbsent(translatedWord, k -> new AtomicInteger(0)).incrementAndGet();
        }
    }

    public int getWordCount(String word) {
    	AtomicInteger count = wordCounts.get(translator.translate(word.toLowerCase()));
        return count == null ? 0 : count.get();
    }

}