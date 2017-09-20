package com.qiao.java8.parameterization;


import com.qiao.java8.data.User;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/17 10:22
 */
public class Filter {
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T e : list) {
			if (p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}
	/*static <R,T> List<R> map(List<T> list, Function<T,R> f){
		List<R> result=new ArrayList<>();
		for(T t:list)
			result.add(f.apply(t));
		return result;
	}
	static String processFile(FileProcessor processer)throws IOException{
		try(BufferedReader reader=new BufferedReader(new FileReader("hello.txt"))){
			return processer.processFile(reader);
		}
	}*/

	public static void main(String[] args) throws IOException {
		List<User> lists = Arrays.asList(
				new User("qiao", 19),
				new User("qiao1", 24),
				new User("qiao2", 25),
				new User("qiao", 19));

//		List<User> result = filter(lists, (User user1) -> user1.getAge() > 19);

		Long start = System.currentTimeMillis();
//		lists.sort(Comparator.comparingInt(User::getAge));
		Map<String, List<User>> result=lists.stream()
//								.filter(d->d.getAge()<25)
								.sorted(Comparator.comparing(User::getAge))
								.collect(groupingBy(User::getUsername));

		Long stop = System.currentTimeMillis();
		Long total = stop - start;
		System.out.println(total);
		System.out.println(result.values());
	}

}
