package com.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@author  liuguangqiang
 *@date    2012-9-24 下午05:57:07
 *@version 1.0
 **/
	@Retention(RetentionPolicy.RUNTIME)  
	@Target({ ElementType.METHOD })  
	public @interface Anno {  
	    String isAop() default "Y";  
}
