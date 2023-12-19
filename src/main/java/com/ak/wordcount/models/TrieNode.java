package com.ak.wordcount.models;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TrieNode {
	
	ConcurrentHashMap<Character, TrieNode> children;
	AtomicInteger endOfWordCount;
	
	public TrieNode() {
		children = new ConcurrentHashMap<Character, TrieNode>();
		endOfWordCount = new AtomicInteger(0);
	}

}
