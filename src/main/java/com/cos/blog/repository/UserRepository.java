package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;


public interface UserRepository extends JpaRepository<User,Integer> {

	User findByUsernameAndPassword(String username,String password);
	
	
}
