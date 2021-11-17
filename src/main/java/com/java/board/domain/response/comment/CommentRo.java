package com.java.board.domain.response.comment;

import com.java.board.domain.response.user.UserRo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentRo {
	private Long id;
	private String comment;
	private UserRo user;
}
