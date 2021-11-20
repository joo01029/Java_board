package com.java.board.domain.response.board;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailRo {
	private Long id;
	private String title;
	private String content;
	private Date lastTime;
	private String writer;
}
