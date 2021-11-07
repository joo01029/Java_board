package com.java.board.domain.response.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRo {
	private Long id;
	private String name;
}
