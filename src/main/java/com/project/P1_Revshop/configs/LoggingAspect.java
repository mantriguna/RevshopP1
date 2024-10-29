package com.project.P1_Revshop.configs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	//
	//for reference
	//
	@Before("execution(* com.project.P1_Revshop.service.*.*(..))")
	public void logBefore(JoinPoint joinpoint) {
		System.out.println("Before      ::: "+joinpoint.getSignature());
	}
	
	@After("execution(* com.project.P1_Revshop.service.*.*(..))")
	public void logAfter(JoinPoint joinpoint) {
		System.out.println("After       ::: "+joinpoint.getSignature());
	}
	@AfterReturning(pointcut="execution(* com.project.P1_Revshop.service.*.*(..))",returning="response")
	public void logAfterReturning(JoinPoint joinpoint,Object response) {
		System.out.println("AfterReturn ::: "+joinpoint.getSignature()+" "+response);
		
	}
	@AfterThrowing(pointcut="execution(* com.project.P1_Revshop.service.*.*(..))", throwing="error")
	public void logAfterThrowing(JoinPoint joinpoint,Throwable error) {
		System.out.println("Error :::: "+error);
	}
}
