package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

@RestController
public class DummyControllerTest {
	
	// 컨트롤러가 메모리에 올라갈 때 자동으로 해당 객체를 주입해줌 (의존성 주입 DI)
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/dummy/user")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// {id} -> 주소로 파라미터를 전달받을 수 있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) { // 주소에 받아온 파라미터를 가져옴
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user/page?page=0 -> 쿼리스트링 '?page = 숫자' 를 적어주면 페이지 이동이 가능
	@GetMapping("/dummy/user/page")
	public Page<User> pageList(@PageableDefault(size=1, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUsers= userRepository.findAll(pageable);
		
		// Page 타입으로 받아온 객체를 List 타입으로 리턴하는 것이 좋음
		List<User> users = pagingUsers.getContent();
		return (Page<User>) users;
	}
	
	@PostMapping("/dummy/join")
	public String join(User u) {
		
		System.out.println("id : " + u.getId());
		System.out.println("username : " + u.getUsername());
		System.out.println("password : " + u.getPassword());
		System.out.println("email : " + u.getEmail());
		
		u.setRole(RoleType.USER);
		userRepository.save(u);
		
		return "회원가입이 완료되었습니다.";
	}
	
	// save()는 id를 전달하지 않으면 insert를 해주고,
	// 전달 시 해당 id에 대한 데이터가 있으면 update를 해주고 해당 id에 대한 데이터가 없으면 insert를 해줌
	// @RequestBody는 폼태그가 아닌 JSON으로 데이터를 요청 (이는 메시지컨버터에 의해 자동으로 자바오브젝트로 변환됨
	@Transactional // 함수 종료 시에 자동으로 commit이 됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { 
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다.");
		});
		
		// @Trancational이 영속화된 데이터가 변경 시 자동으로 업데이트를 해줌 (더티 체킹)
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// userRepository.save(user);
		
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		
		return "삭제되었습니다. id : " + id;
	}
}
