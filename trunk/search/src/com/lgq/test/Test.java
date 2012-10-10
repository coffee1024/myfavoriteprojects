package com.lgq.test;

import com.lgq.dao.DomainDao;
import com.lgq.domain.Domain;

/**
 *@author  liuguangqiang
 *@date    2012-10-10 下午05:42:49
 *@version 1.0
 **/
public class Test {
	public static void main(String[] args) {
		DomainDao dao=new DomainDao();
		Domain domain=new Domain();
		domain.setContent("123123123123");
		dao.addDomain(domain);
	}
}
