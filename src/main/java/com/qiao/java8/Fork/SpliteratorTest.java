package com.qiao.java8.Fork;

import com.qiao.java8.util.MeasureTime;
import com.qiao.java8.util.WordCounter;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/17 20:51
 */
public class SpliteratorTest implements Spliterator<Character> {
	private final String s;
	private int currentChar=0;

	public SpliteratorTest(String s) {
		this.s = s;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		action.accept(s.charAt(currentChar++));
		return currentChar<s.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		int currentSize=s.length()-currentChar;
		if(currentSize<10)
			return null;
		for(int pos=currentSize/2+currentChar;pos<s.length();pos++){
			if(Character.isWhitespace(s.charAt(pos))){
				Spliterator<Character> spliterator=new SpliteratorTest(s.substring(currentChar,pos));
				currentChar=pos;
				return spliterator;
			}
		}
		return null;
	}

	@Override
	public long estimateSize() {
		return 0;
	}

	@Override
	public int characteristics() {
		return 0;
	}

	public static void main(String[] args){
		String test = "hahaha dwidjw dwjixw wopoc  wdjjd wwdw  wkozx mxxcp";
		Stream<Character> stream = IntStream.rangeClosed(0, test.length())
				.mapToObj(test::charAt);
		System.out.println(MeasureTime.measure(WordCounter::countWords,stream));
	}

}
