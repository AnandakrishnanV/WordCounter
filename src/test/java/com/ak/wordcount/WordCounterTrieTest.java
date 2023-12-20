package com.ak.wordcount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ak.wordcount.interfaces.Translator;

/**
 * All tests pass
 * Commenting them out since not using the implementation
 */

@ExtendWith(MockitoExtension.class)
public class WordCounterTrieTest {
	
//	private WordCounterTrie wordCounter;
//
//	 @Mock
//	 private Translator translator;
//	 
//	 @BeforeEach
//	    public void setUp() {
//	        wordCounter = new WordCounterTrie(translator);
//	    }
//	
//	@Test
//    public void testAddValidWord() {
//		when(translator.translate("flower")).thenReturn("flower");
//		
//        wordCounter.addWords("flower");
//        assertEquals(1, wordCounter.getWordCount("flower"));
//    }
//	
//	@Test
//	public void testAddMultipleWords() {
//		when(translator.translate("flower")).thenReturn("flower");
//		when(translator.translate("sunflower")).thenReturn("sunflower");
//		
//		wordCounter.addWords("flower sunflower");
//        assertEquals(1, wordCounter.getWordCount("flower"));
//        assertEquals(1, wordCounter.getWordCount("sunflower"));
//	}
//	
//	@Test
//    public void testAddWordWithNonAlphabeticCharacters() {
//		when(translator.translate("flower")).thenReturn("flower");
//		when(translator.translate("sunflower")).thenReturn("sunflower");
//		// when(translator.translate("flower123")).thenReturn("flower123");
//		
//        wordCounter.addWords("flower flower123 sunflower");
//        assertEquals(1, wordCounter.getWordCount("sunflower"));
//        assertEquals(1, wordCounter.getWordCount("flower"));
//        // Since "flower123" is invalid, it should not be added, and its count should be 0
//        assertEquals(0, wordCounter.getWordCount("flower123"));
//    }
//	
//	@Test
//    public void testTranslationAndAddition() {
//        when(translator.translate("flor")).thenReturn("flower");
//        when(translator.translate("blume")).thenReturn("flower");
//        when(translator.translate("flower")).thenReturn("flower");
//
//        wordCounter.addWords("flor blume");
//        assertEquals(2, wordCounter.getWordCount("flower"));
//    }
//
//    @Test
//    public void testGetCountOfNonExistingWord() {
//    	when(translator.translate("rose")).thenReturn("rose");
//    	
//        assertEquals(0, wordCounter.getWordCount("rose"));
//    }
//    
//    @Test
//    public void testConcurrentWordAddition() {
//    	when(translator.translate("flower")).thenReturn("flower");
//    	
//        String text = IntStream.range(0, 10000)
//                               .mapToObj(i -> "flower")
//                               .collect(Collectors.joining(" "));
//
//        // Simulating concurrent access by adding the same text in multiple threads
//        IntStream.range(0, 10).parallel().forEach(i -> wordCounter.addWords(text));
//
//        // Each "flower" in the text is added 10,000 times in 10 different threads
//        assertEquals(100000, wordCounter.getWordCount("flower"));
//    }
}
