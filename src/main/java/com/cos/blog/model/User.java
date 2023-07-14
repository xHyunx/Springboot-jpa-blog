package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; 


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @DynamicInsert  // insert 시에 null인 필드를 제외해줌
// ORM -> Object -> 테이블로 매핑해주는 어노테이션
@Entity  // 클래스가 자동으로 MySQL에 테이블로 생성됨
public class User {
	
	@Id  // Primary key로 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라간다.
	private int id;
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("'user'") 값이 insert될 때 해당 컬럼에 값이 없으면 지정된 기본 값을 넣어줌
	@Enumerated(EnumType.STRING) // 해당 Enum의 타입이 스트링이라는 것을 알려줌
	private RoleType role;
	
	@CreationTimestamp  // 시간이 자동으로 입력
	private Timestamp createDate;
}
