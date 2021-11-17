package com.java.board.config.filter;


import com.java.board.enums.JwtAuth;
import com.java.board.enums.Role;
import com.java.board.lib.CheckJwtType;
import com.java.board.lib.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtFilter implements Filter {
	private final HandlerExceptionResolver handlerExceptionResolver;
	private final JwtProvider jwtProvider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			String token = CheckJwtType.extract((HttpServletRequest) request, "Bearer");
			if (((HttpServletRequest) request).getMethod() != "OPTIONS") {
				Set<GrantedAuthority> roles = new HashSet<>();

				if (null == token) {
					request.setAttribute("idx", null);
					roles.add(new SimpleGrantedAuthority("ROLE_" + Role.USER.toString()));
				} else {
					Claims claims = jwtProvider.validToken(token, JwtAuth.ACCESS);
					request.setAttribute("idx", claims.get("idx"));
					roles.add(new SimpleGrantedAuthority("ROLE_" + claims.get("role").toString()));
				}
				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, null, roles));
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			handlerExceptionResolver.resolveException((HttpServletRequest) request, (HttpServletResponse) response, null, e);
		}
	}
}
