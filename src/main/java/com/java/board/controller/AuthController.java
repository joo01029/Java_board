package com.java.board.controller;


import com.java.board.auth.AuthService;
import com.java.board.domain.dto.auth.LoginDto;
import com.java.board.domain.dto.auth.RegisterDto;
import com.java.board.domain.response.Response;
import com.java.board.domain.response.ResponseData;
import com.java.board.domain.response.auth.JwtResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/register")
	public Response register(@RequestBody RegisterDto registerDto){
		authService.register(registerDto);
		return new Response();
	}

	@PostMapping("/login")
	public ResponseData<JwtResult> login(@RequestBody LoginDto loginDto){
		JwtResult data = authService.login(loginDto);
		return new ResponseData<>(data);
	}

	@PostMapping("/refresh")
	public ResponseData<JwtResult> refresh(@RequestParam String refreshToken){
		JwtResult data = authService.refreshToken(refreshToken);
		return new ResponseData<>(data);
	}
}
