package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	@GetMapping("/temp/home")
	public String tempHome() {
		// 파일 리턴의 기본경로 : src/main/resourses/static
		// 그래서 파일이 제대로 리턴되려면 파일명 앞에 '/'를 붙여줘야 함
		
		System.out.println("temphome()");
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		return "test";
	}
}
