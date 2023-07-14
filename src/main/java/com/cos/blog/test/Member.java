package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// @Getter - getter 메서드 생성
// @Setter - setter 메서드 생성
//@RequiredArgsConstructor - final이 붙은 인스턴스 변수에 대한 생성자를 만들어줌
/*@AllArgsConstructor*/ /*- 모든 변수를 사용한 생성자를 만들어줌*/
@NoArgsConstructor /* - 기본 생성자를 만들어줌 */
@Data
public class Member {
	private int id;
	private String username;
	private String password;
	private String email; 
	
	@Builder /*- 인스턴스 변수를 내가 원하는 것만 순서 상관없이 넣어 객체생성 가능한 생성자를 만들어 줌*/
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
