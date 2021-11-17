package com.java.board.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class Logging {
	@Pointcut("@annotation(com.java.board.annotation.AutoLogging)")
	public void logging(){}

	@Before("logging()")
	public void beforeService(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		String ip= getIp();

		log.info(makeLog(className,methodName,ip));
	}

	@Around("logging()")
	public Object getTaskTime(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		String ip= getIp();

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object proceed;
		try {
			proceed = joinPoint.proceed();
		} catch (Throwable throwable) {

			throw throwable;
		}finally {
			stopWatch.stop();
			log.info(makeLog(className,methodName,ip, stopWatch.getTotalTimeMillis()));
		}
		return proceed;
	}

	private String makeLog(String className, String methodName, String ip){
		return new StringBuffer().append("\n")
				.append("class  : ").append(className).append("\n")
				.append("method : ").append(methodName).append("\n")
				.append("ip     : ").append(ip).toString();
	}
	private String makeLog(String className, String methodName, String ip, Long taskTime){
		return new StringBuffer().append("\n")
				.append("class    : ").append(className).append("\n")
				.append("method   : ").append(methodName).append("\n")
				.append("ip       : ").append(ip).append("\n")
				.append("taskTime :").append(taskTime).toString();
	}

	private String getIp(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return decodingHeaderId(request);
	}

	private String decodingHeaderId(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded_For");
		if(ip == null){
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
