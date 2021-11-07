package com.java.board.domain.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRo {
	private Long id;
	private String name;
}
