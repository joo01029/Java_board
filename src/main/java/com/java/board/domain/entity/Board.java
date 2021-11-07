package com.java.board.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

	@OneToMany(cascade = CascadeType.ALL)
	private List<Like> likes;



	public void add(Like like){
		this.likes.add(like);
	}
}
