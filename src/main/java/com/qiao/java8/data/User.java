package com.qiao.java8.data;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/17 10:22
 */
public class User {
	private String username;
	private Long age;

	public User() {
	}

	public User(String username, Long age) {
		this.username = username;
		this.age = age;
	}

	@Override
	public String toString() {
		return this.getUsername()+"'s age="+this.getAge();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}
}
