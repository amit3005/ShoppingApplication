package com.sheryians.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.model.User;

public interface UserREPO extends JpaRepository<User, Integer> {
	
	User findUserByEmailId(String emailId);
}
