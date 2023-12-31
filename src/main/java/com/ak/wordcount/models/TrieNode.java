package com.ak.wordcount.models;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Testing the Trie Approach
 * 
 */
public class TrieNode {
	
	public ConcurrentHashMap<Character, TrieNode> children;
	public AtomicInteger endOfWordCount;
	
	public TrieNode() {
		children = new ConcurrentHashMap<Character, TrieNode>();
		endOfWordCount = new AtomicInteger(0);
	}

}
