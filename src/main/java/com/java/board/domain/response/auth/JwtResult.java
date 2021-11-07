package com.java.board.domain.response.auth;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResult {
	private String accessToken;
	private Date accessExpiredAt;
	private String refreshToken;
	private Date refreshExpiredAt;
}
