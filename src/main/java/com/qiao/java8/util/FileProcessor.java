package com.qiao.java8.util;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/17 12:24
 */
@FunctionalInterface
public interface FileProcessor {
	String processFile(BufferedReader reader) throws IOException;

	default boolean exists(BufferedReader reader) throws IOException{
		return reader.read()!=-1;
	}
}
