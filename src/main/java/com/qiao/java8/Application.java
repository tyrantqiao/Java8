package com.qiao.java8;

import com.qiao.java8.collector.PrimeNumbersCollector;
import com.qiao.java8.util.MeasureTime;
import com.qiao.java8.util.WordCounter;

import java.util.List;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/17 10:18
 */
public class Application {
	public static Long parallelSum(long n) {
		return Stream.iterate(1L, i -> i + 1)
				.limit(n)
				.parallel()
				.reduce(0L, Long::sum);
	}



	public static void main(String[] args) {
//		System.out.println(parallelSum(1000_000L));
//		Long time = MeasureTime.measureFastest(Application::parallelSum, 1000_000L);
//		System.out.println(time);

	}
}
