package com.qiao.java8.future;

import com.qiao.java8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/20 22:00
 */
public class FutureTool {

	private UserRepository repository;

	@Autowired
	public void setRepository(UserRepository repository){
		this.repository=repository;
	}

	public Future<Long> getUserTreasure(String userName){
		CompletableFuture<Long> future=new CompletableFuture<>();
		new Thread(()->{
			try {
				Long treasure = repository.getUserByAge();
				future.complete(treasure);
			}catch (Exception e){
				future.completeExceptionally(e);
			}
		}).start();
		return future;
	}

}
