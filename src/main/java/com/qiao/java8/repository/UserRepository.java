package com.qiao.java8.repository;

import com.qiao.java8.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/20 22:27
 */
public interface UserRepository extends Repository<User,Long> {
	Long getUserByAge();
}
