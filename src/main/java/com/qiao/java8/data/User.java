package com.qiao.java8.data;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/17 10:22
 */
public class User {
	private String name;
	private int age;

	public User() {
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return this.getName()+"'s age="+this.getAge();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
