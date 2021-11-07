package com.java.board.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@Table(indexes = {@Index(columnList = "id")})
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_idx")
	private User user;

	public void add(User user){
		this.user = user;
		user.getBoards().add(this);
	}
}
