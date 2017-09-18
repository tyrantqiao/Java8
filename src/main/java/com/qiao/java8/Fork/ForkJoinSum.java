package com.qiao.java8.Fork;

import com.qiao.java8.util.MeasureTime;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/17 20:14
 */
public class ForkJoinSum extends RecursiveTask<Long> {
	private long[] nums;
	private int start;
	private int end;
	private static final int threshold = 1000;

	public ForkJoinSum(long[] nums) {
		this(nums, 0, nums.length);
	}

	public ForkJoinSum(long[] nums, int start, int end) {
		this.nums = nums;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		int length = end - start;
		if (length <= threshold) {
			return computeSequentially();
		}
		ForkJoinSum leftTask = new ForkJoinSum(nums, start, start + length / 2);
		leftTask.fork();
		ForkJoinSum rightTask = new ForkJoinSum(nums, start + length / 2, end);
		Long rightResult = rightTask.compute();
		Long leftResult = leftTask.compute();
		return rightResult + leftResult;
	}

	@org.jetbrains.annotations.Contract(pure = true)
	private Long computeSequentially() {
		long sum = 0;
		for (int i = start; i < end; i++) {
			sum += nums[i];
		}
		return sum;
	}

	private static Long getSum(Long n){
		long[] nums = LongStream.rangeClosed(1, n).toArray();
		ForkJoinSum task = new ForkJoinSum(nums);
		return new ForkJoinPool().invoke(task);
	}

	public static void main(String[] args) {
		System.out.println(getSum(1000_000L));
		System.out.println(MeasureTime.measureFastest(ForkJoinSum::getSum,  1000_000L));
//		MeasureTime.measureFastest(pool,task)
	}
}
