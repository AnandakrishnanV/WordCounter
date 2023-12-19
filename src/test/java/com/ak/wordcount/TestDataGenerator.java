package com.ak.wordcount;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestDataGenerator {

	private static final String[] SAMPLE_WORDS = { "flower", "tree", "sunflower", "daisy", "rose", "flor", "orchid",
			"blume"};
	
	private static final String[] SAMPLE_WORDS_WITH_INVALIDS = { "flower", "tree", "sunflower", "da33isy", "rose", "4ora"};

	private static final Random random = new Random();

	public static String generateLargeTextSet(int wordCount) {
		return IntStream.range(0, wordCount).mapToObj(i -> SAMPLE_WORDS[random.nextInt(SAMPLE_WORDS.length)])
				.collect(Collectors.joining(" "));
	}

	public static String generateLargeTextSetWithInvalidWords(int wordCount) {
		return IntStream.range(0, wordCount).mapToObj(i -> SAMPLE_WORDS_WITH_INVALIDS[random.nextInt(SAMPLE_WORDS_WITH_INVALIDS.length)])
				.collect(Collectors.joining(" "));
	}
}
