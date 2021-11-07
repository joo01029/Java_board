package com.java.board.domain.response.board;

import com.java.board.domain.response.user.UserRo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardRo {
	private Long id;
	private String title;
	private String content;
	private Long commentNum = 0L;
	private Long likeNum = 0L;
	private UserRo user;
}
