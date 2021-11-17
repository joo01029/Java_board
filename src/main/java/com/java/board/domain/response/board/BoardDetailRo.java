package com.java.board.domain.response.board;

import com.java.board.domain.response.user.UserRo;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailRo {
	private Long id;
	private String title;
	private String content;
	private Boolean isMine;
	private UserRo user;
}
