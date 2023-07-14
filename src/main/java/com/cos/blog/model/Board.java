package com.cos.blog.model;


import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob  // 대용량 데이터에 사용
	private String content;
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne  // Many = Board, One = User  FetchType은 EAGER가 기본(반드시 들고오라)
	@JoinColumn(name = "userId") // 컬럼 생성 시 이름은 'userId'로 생성
	private User user; // DB는 오브젝트를 저장할 수 없지만, 자바는 오브젝트를 저장 가능하다.
	
	// OneToMany는 FetchType이 LAZY가 기본 (필요시 들고오라)
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)  // mappedBy 연관관계의 주인이 아니다(FK 아님) DB에 컬럼을 만들지 말아라
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
