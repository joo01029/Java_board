package com.java.board.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {@Index(columnList = "id")})
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String comment;

	@ManyToOne
	@JoinColumn(name = "user_idx")
	private User user;

	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;
}
