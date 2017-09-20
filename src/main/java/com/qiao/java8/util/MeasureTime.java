package com.qiao.java8.util;

import java.util.function.Function;
import java.util.function.LongFunction;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/17 16:22
 */
public class MeasureTime {

	public static <T, R> Long measure(Function<T, R> f, T t) {
		Long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			Long start = System.currentTimeMillis();
			f.apply(t);
			Long total = System.currentTimeMillis() - start;
			if (total < fastest) fastest = total;
		}
		return fastest;
	}


	public static Long measureFastest(LongFunction<Long> adder, Long n) {
		Long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			Long start = System.currentTimeMillis();
			adder.apply(n);
			Long total = System.currentTimeMillis() - start;
			if (total < fastest) fastest = total;
		}
		return fastest;
	}
}
