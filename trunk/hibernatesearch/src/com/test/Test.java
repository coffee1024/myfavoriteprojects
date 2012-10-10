package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.TestDao;

/**
 *@author  liuguangqiang
 *@date    2012-9-24 下午04:45:44
 *@version 1.0
 **/
@Controller
@RequestMapping ("/test.do")
public class Test {
	@Autowired
	private TestDao td; 
	@RequestMapping
	public String test1(){
		System.out.println("success");
		Domain domain=new Domain();
		domain.setContent("按照这一方案，日本政府可在不改变既有主张的同时对中国的态度予以顾及。但是，即便日方采取上述立场，中国方面能否接受并为改善关系采取行动尚难预料。日本政府计划在仔细观察中方今后的态度之后决定是否提出妥协方案。 ");
		td.save(domain);
		return "index";
	}
}
