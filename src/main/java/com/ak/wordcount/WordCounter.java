package com.ak.wordcount;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

import com.ak.wordcount.interfaces.Translator;
import com.ak.wordcount.util.CheckWordValidity;

public class WordCounter {

	private final Translator translator;
    private final ConcurrentHashMap<String, AtomicInteger> wordCounts;
    private final int batchSize = 10000; // Adjustable based on performance needs

    public WordCounter(Translator translator) {
        this.translator = translator;
        this.wordCounts = new ConcurrentHashMap<>();
    }

    public void addWord(String text) {
        List<String> words = Arrays.asList(text.toLowerCase().split("\\W+"));
        ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        try {
            customThreadPool.submit(() -> 
                words.parallelStream().forEach(word -> {
                    if (!CheckWordValidity.check(word)) {
                        return; // Skip words with non-alphabetic characters
                    }
                    String translatedWord = translator.translate(word);
                    wordCounts.compute(translatedWord, (key, count) -> {
                        if (count == null) return new AtomicInteger(1);
                        count.incrementAndGet();
                        return count;
                    });
                })
            ).get(); // Blocks until all tasks are completed
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error processing words in parallel", e);
        } finally {
            customThreadPool.shutdown(); // Important to prevent resource leakage
        }
    }

    public int getWordCount(String word) {
        if (!CheckWordValidity.check(word)) {
            return 0; // Return 0 for invalid words
        }
        String translatedWord = translator.translate(word.toLowerCase());
        return wordCounts.getOrDefault(translatedWord, new AtomicInteger(0)).get();
    }
}