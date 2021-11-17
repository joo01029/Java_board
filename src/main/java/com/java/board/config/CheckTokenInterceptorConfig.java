package com.java.board.config;

import com.java.board.config.Interceptor.CheckTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CheckTokenInterceptorConfig implements WebMvcConfigurer {
	@Autowired
	private CheckTokenInterceptor checkTokenInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(checkTokenInterceptor)
				.addPathPatterns("/user")
				.addPathPatterns("/board")
				.addPathPatterns("/board/**");
	}
}
