package com.qiao.java8.collector;


import com.sun.javafx.collections.MappingChange;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/17 15:11
 */
public class PrimeNumbersCollector implements Collector<Integer,
		Map<Boolean, List<Integer>>,
		Map<Boolean, List<Integer>>> {

	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> new HashMap<Boolean, List<Integer>>() {{
			put(true, new ArrayList<Integer>());
			put(false, new ArrayList<Integer>());
		}};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
			acc.get(isPrime(acc.get(true), candidate)).add(candidate);
		};
	}

	private Boolean isPrime(List<Integer> integers, Integer candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return takeWhile(integers, i -> i <= candidateRoot)
				.stream()
				.noneMatch(p -> candidate % p == 0);
	}
	private static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
		int i=0;
		for(A item:list) {
			if (!p.test(item)) {
				return list.subList(0, i);
			}
			i++;
		}
		return list;
	}

	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean,List<Integer>> map1,
				Map<Boolean,List<Integer>> map2)->{
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
	}

	public static Map<Boolean,List<Integer>> getPrimesByCollector(int size){
		return IntStream.rangeClosed(2,size)
						.boxed()
						.collect(new PrimeNumbersCollector());
	}
}
