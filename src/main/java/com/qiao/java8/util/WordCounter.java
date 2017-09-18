package com.qiao.java8.util;

import java.util.stream.Stream;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/17 21:05
 */
public class WordCounter {
	private final int counter;
	private final boolean lastSpace;

	public WordCounter(int counter, boolean lastSpace) {
		this.counter = counter;
		this.lastSpace = lastSpace;
	}

	public WordCounter accumulate(Character c){
		if(Character.isWhitespace(c)){
			return lastSpace?
					this:
					new WordCounter(counter,true);
		}else{
			return lastSpace?
					new WordCounter(counter+1,false):
					this;
		}
	}

	public WordCounter combine(WordCounter wordCounter){
		return new WordCounter(counter+wordCounter.counter,wordCounter.lastSpace);
	}

	public int getCounter() {
		return counter;
	}

	public static int countWords(Stream<Character> stream){
		WordCounter wordCounter=stream.reduce(new WordCounter(0,true),
											WordCounter::accumulate,
											WordCounter::combine);
		return wordCounter.getCounter();
	}

	public static void main(String[] args){
	}
}
