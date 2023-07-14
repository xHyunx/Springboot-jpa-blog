package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 예외 발생 시 이 클래스로 오게 해서 예외처리를 함
@RestController
public class GlobalExceptionHadler {
	
	// 모든 예외를 처리하는 방법 : 예외의 최고 조상인 Exception을 지정해주면 됨
//	@ExceptionHandler(value=Exception.class)
//	public String Exception(Exception e) {
//		return "<h1>" + e.getMessage() + "</h1>";
//	}
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
