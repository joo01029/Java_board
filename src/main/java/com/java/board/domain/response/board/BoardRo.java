package com.java.board.domain.response.board;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRo {
	private Long id;
	private String title;
	private String writer;
	private Date lastTime;
}
