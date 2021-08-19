package com.mytoshika.logging;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggerAdvice {

	@Around("execution(* com.mytoshika.service.impl..*(..))")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		log.info("Start :: {} | Args => {}", joinPoint.getSignature(),Arrays.asList(joinPoint.getArgs()));
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		log.info("End :: {} and executed in {} ms", joinPoint.getSignature(), executionTime);

		return proceed;
	}
}
