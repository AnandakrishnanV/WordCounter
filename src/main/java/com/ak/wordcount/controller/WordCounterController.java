package com.ak.wordcount.controller;

import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ak.wordcount.interfaces.IWordCounter;
import com.ak.wordcount.models.FrequencyResponse;
import com.ak.wordcount.models.TextRequest;

/**
 * REST controller for word counting operations.
 * Provides end points for adding words to the counter and retrieving word counts.
 */

@RestController
@RequestMapping("/api/v1/wordcounter")
@Validated
public class WordCounterController {

	private final IWordCounter wordCounter;
	private static final Logger LOGGER = Logger.getLogger(WordCounterController.class.getName());

	@Autowired
	public WordCounterController(IWordCounter wordCounter) {
		this.wordCounter = wordCounter;
	}

	/**
     * End point to add words to the counter.
     * Accepts a JSON payload containing text to be processed.
     *
     * @param request The text string containing the words.
     * @return A ResponseEntity specifying success or failure.
     */
	@PostMapping("/add")
	public ResponseEntity<String> addWords(@Valid @RequestBody TextRequest request) {
		try {
			wordCounter.addWords(request.getText());
			return ResponseEntity.ok("Words added successfully");
		} catch (Exception e) {
			LOGGER.severe("Error adding words: " + e.getMessage());
			return ResponseEntity.internalServerError().body("Error adding words: " + e.getMessage());
		}
	}

	/**
     * End point to retrieve the count of a specific word.
     * The word to be counted is passed as a request parameter.
     *
     * @param word The word for which the count is requested.
     * @return A ResponseEntity containing the word count or an error message.
     */
	@GetMapping("/count")
	public ResponseEntity<?> getWordCount(@RequestParam @NotBlank(message = "Word cannot be blank") String word) {
		try {
			int count = wordCounter.getWordCount(word);
			return ResponseEntity.ok(new FrequencyResponse(word, count));
		} catch (Exception e) {
			LOGGER.severe("Internal Error " + e.getMessage());
			return ResponseEntity.internalServerError().body("An error occurred while processing the request"); 
		}
	}
	
}
