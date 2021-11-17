package com.java.board.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board_like", indexes = {@Index(columnList = "id")})
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_idx")
	private User user;

	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;

}