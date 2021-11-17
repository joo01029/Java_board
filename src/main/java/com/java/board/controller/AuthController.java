package com.java.board.controller;


import com.java.board.annotation.AutoLogging;
import com.java.board.service.auth.AuthService;
import com.java.board.domain.dto.auth.LoginDto;
import com.java.board.domain.dto.auth.RegisterDto;
import com.java.board.domain.response.Response;
import com.java.board.domain.response.ResponseData;
import com.java.board.domain.response.auth.JwtResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@AutoLogging
	@PostMapping("/register")
	public Response register(@RequestBody @Valid RegisterDto registerDto) {
		authService.register(registerDto);
		return new Response();
	}

	@AutoLogging
	@PostMapping("/login")
	public ResponseData<JwtResult> login(@RequestBody LoginDto loginDto) {
		JwtResult data = authService.login(loginDto);
		return new ResponseData<>(data);
	}

	@AutoLogging
	@PostMapping("/refresh")
	public ResponseData<JwtResult> refresh(
			@Valid
			@RequestParam
			@NotEmpty(message = "공백 불가능")
					String refreshToken) {
		JwtResult data = authService.refreshToken(refreshToken);
		return new ResponseData<>(data);
	}
}
