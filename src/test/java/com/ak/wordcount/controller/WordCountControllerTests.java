package com.ak.wordcount.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class WordCountControllerTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddWords() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/wordcounter/add")
                .contentType("application/json")
                .content("{\"text\":\"The quick brown fox\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Words added successfully"));
    }
    
    @Test
    public void testGetWordCount() throws Exception {
        // Assuming 'fox' was added in the previous test or setup
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/wordcounter/count")
                .param("word", "fox"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("\"word\":\"fox\",\"count\":1")));
    }

}
