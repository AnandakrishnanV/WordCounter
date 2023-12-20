package com.ak.wordcount.models;

import javax.validation.constraints.NotBlank;

public class TextRequest {
	
	@NotBlank(message = "Text cannot be blank")
    private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
