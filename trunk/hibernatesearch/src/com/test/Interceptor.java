package com.test;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 *@author  liuguangqiang
 *@date    2012-9-24 下午05:23:29
 *@version 1.0
 **/
@Aspect
public class Interceptor {
	@Pointcut("execution(com.test.Domain.*(..))")
	private void anyMethod() {}//声明一个切入点
	@Before("anyMethod()")
	public void before(){
		System.out.println("前置通知");
	}
	@After("anyMethod()")
	public void after(){
		System.out.println("后置通知");
	}
}
