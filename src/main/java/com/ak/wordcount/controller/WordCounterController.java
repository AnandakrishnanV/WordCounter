package com.ak.wordcount.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ak.wordcount.WordCounter;
import com.ak.wordcount.interfaces.IWordCounter;

@RestController
@RequestMapping("/api/v1/wordcounter")
public class WordCounterController {

	private final IWordCounter wordCounter;
	private static final Logger LOGGER = Logger.getLogger(WordCounter.class.getName());
	
    @Autowired
    public WordCounterController(IWordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addWords(@RequestBody String text) {
        try {
            wordCounter.addWords(text);
            return ResponseEntity.ok("Words added successfully");
        } catch (Exception e) {
        	LOGGER.severe("Error adding words: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error adding words: " + e.getMessage());
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getWordCount(@RequestParam String word) {
        try {
            int count = wordCounter.getWordCount(word);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
        	LOGGER.severe("Internal Error " + e.getMessage());
            return ResponseEntity.internalServerError().body(-1); // or any other error representation
        }
    }
}
