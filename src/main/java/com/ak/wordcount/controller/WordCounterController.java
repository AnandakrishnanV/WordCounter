package com.ak.wordcount.controller;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ak.wordcount.interfaces.IWordCounter;
import com.ak.wordcount.models.FrequencyResponse;
import com.ak.wordcount.models.TextRequest;

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

	@PostMapping("/add")
	public ResponseEntity<?> addWords(@Valid @RequestBody TextRequest request) {
		try {
			wordCounter.addWords(request.getText());
			return ResponseEntity.ok("Words added successfully");
		} catch (Exception e) {
			LOGGER.severe("Error adding words: " + e.getMessage());
			return ResponseEntity.internalServerError().body("Error adding words: " + e.getMessage());
		}
	}

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
