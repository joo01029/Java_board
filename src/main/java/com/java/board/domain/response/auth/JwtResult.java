package com.java.board.domain.response.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Builder
public class JwtResult {
	private String accessToken;
	private Date accessExpiredAt;
	private String refreshToken;
	private Date refreshExpiredAt;
}
