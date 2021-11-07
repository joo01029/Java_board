package com.java.board.domain.response.board;

import com.java.board.domain.response.user.UserRo;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRo {
	private Long id;
	private String title;
	private String content;
	private Long commentNum = 0L;
	private Long likeNum = 0L;
	private Boolean likeStatus = false;
	private UserRo user;
}
