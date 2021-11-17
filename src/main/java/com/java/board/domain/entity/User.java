package com.java.board.domain.entity;

import com.java.board.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {@Index(columnList = "id")})
public class User {
	@Id
	@GeneratedValue
	private Long idx;

	@Column(unique = true, length = 50)
	private String id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
	private List<Board> boards = new ArrayList<>();
	@OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
	private List<Like> likes = new ArrayList<>();
	@OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
	private List<Comment> Comments = new ArrayList<>();

	public void add(Board board) {
		board.setUser(this);
		this.getBoards().add(board);
	}
	public void add(Comment comment) {
		comment.setUser(this);
		this.getComments().add(comment);
	}
}
