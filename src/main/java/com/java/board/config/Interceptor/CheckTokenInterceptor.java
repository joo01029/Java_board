package com.java.board.config.Interceptor;

import com.java.board.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getAttribute("idx") != null){
			return true;
		}

		throw new CustomException(HttpStatus.FORBIDDEN, "토큰 필요");
	}
}
