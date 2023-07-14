package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> HTML 파일을 응답 (@Controller)
// 사용자가 요청 -> 데이터를 응답 (@RestCotroller)
@RestController
public class HttpConrollerTest {
	
	// 인터넷 브라우저 요청은 무조건 get 요청만 가능
	//@RequestParam으로 쿼리 스트링 받아오기 가능
	@GetMapping("/http/get")
	public String getTest(Member m) {
		/* builder를 통해서 원하는 객체를 생성 */
		Member m2 = Member.builder().id(5).build();
		
		return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getEmail();
	}
	
	// post는 쿼리 스트링이 아닌 바디에 정보를 담아서 보내야 함
	// @RequestBody로 바디의 정보를 받아오기 가능
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" + m.getId() + ", " + m.getUsername() + ", " + m.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
