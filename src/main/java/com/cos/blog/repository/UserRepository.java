package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// 자동으로 bean으로 등록이 되므로 어노테이션이 없어도 됨
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
