package com.ak.wordcount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ak.wordcount.interfaces.Translator;

@ExtendWith(MockitoExtension.class)
public class WordCounterTest {
	
	private WordCounter wordCounter;

	 @Mock
	 private Translator translator;
	 
	 @BeforeEach
	    public void setUp() {
	        wordCounter = new WordCounter(translator);
	    }
	
	@Test
    public void testAddValidWord() {
		when(translator.translate("flower")).thenReturn("flower");
		
        wordCounter.addWord("flower");
        assertEquals(1, wordCounter.getWordCount("flower"));
    }
	
	@Test
	public void testAddMultipleWords() {
		when(translator.translate("flower")).thenReturn("flower");
		when(translator.translate("sunflower")).thenReturn("sunflower");
		
		wordCounter.addWord("flower", "sunflower");
        assertEquals(1, wordCounter.getWordCount("flower"));
        assertEquals(1, wordCounter.getWordCount("sunflower"));
	}
	
	@Test
    public void testAddWordWithNonAlphabeticCharacters() {
        assertThrows(IllegalArgumentException.class, () -> wordCounter.addWord("flower123"));
    }
	
	@Test
    public void testTranslationAndAddition() {
        when(translator.translate("flor")).thenReturn("flower");
        when(translator.translate("blume")).thenReturn("flower");
        when(translator.translate("flower")).thenReturn("flower");

        wordCounter.addWord("flor", "blume");
        assertEquals(2, wordCounter.getWordCount("flower"));
    }

    @Test
    public void testGetCountOfNonExistingWord() {
    	when(translator.translate("rose")).thenReturn("rose");
    	
        assertEquals(0, wordCounter.getWordCount("rose"));
    }
}
