package com.java.board.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	@OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
	private List<Like> likes;

	@OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
	private List<Comment> comments;


	public void add(Like like){
		like.setBoard(this);
		this.likes.add(like);
	}

	public void add(Comment comment){
		comment.setBoard(this);
		this.comments.add(comment);
	}
}
