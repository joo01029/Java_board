package com.java.board.auth;

import com.java.board.domain.dto.auth.LoginDto;
import com.java.board.domain.dto.auth.RegisterDto;
import com.java.board.domain.response.auth.JwtResult;

public interface AuthService {
	void register(RegisterDto registerDto);
	JwtResult login(LoginDto loginDto);

	JwtResult refreshToken(String refreshToken);
}
