package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;


// 각 클래스에 맞는 어노테이션을 붙여줘야 스프링이 컨포넌트 스캔을 통해 Bean으로 등록해줌
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional // 서비스를 하나의 트랜잭션으로 묶고 안에 로직이 하나라도 잘못 수행되면 rollbakc됨
	public int 회원가입(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("UserService : 회원가입() - " + e.getMessage());;
		}
		
		return -1;
	}
}
