package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@author  liuguangqiang
 *@date    2012-9-24 下午04:45:44
 *@version 1.0
 **/
@Controller
@RequestMapping ("/test.do")
public class Test {
	@Autowired
	private Domain domain; 
	@RequestMapping
	public String test1(){
		System.out.println("success");
		domain.save("lgq");
		return "index";
	}
}
