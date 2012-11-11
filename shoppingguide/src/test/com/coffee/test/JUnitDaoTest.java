package com.coffee.test;

import java.util.List;

import org.ansj.lucene3.AnsjAnalysis;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.util.Version;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coffee.core.orm.Page;
import com.coffee.dao.TestDomainDao;
import com.coffee.domain.TestDomain;
import com.coffee.service.TestDomainManager;

/**
 *@author  liuguangqiang
 *@date    2012-10-24 上午08:29:54
 *@version 1.0
 **/
public class JUnitDaoTest extends AbstractBaseTestCase {
	TestDomainManager ffd;
	/**
	 * 使用spring的配置获取相关的类
	 */
	@Before
	public void strat(){
		ffd=fscontext.getBean(TestDomainManager.class);
		System.out.println("start");
	}
	@After
	public void end(){
		System.out.println("end");
	}
	/**
	 * 更新老的图片操作记录插入图片作者id和name
	 */
	@Test
	public void test(){
//		for(int i=1;i<10;i++){
//			TestDomain  ff=new TestDomain();
//			ff.setContent("中华人民共和国你好大中国北京南京"+i);
//			ffd.saveFullText(ff);
//		}
//		System.out.println(ffd.fullTextQuary("中华人民共和国"));
		BooleanClause.Occur[] flags=new BooleanClause.Occur[]{BooleanClause.Occur.SHOULD};
		Page<TestDomain> page=new Page<TestDomain>(3);
		page.setPageNo(2);
		page=ffd.fullTextPageQuary(page, new String[]{"content"}, new String[]{"中华人民共和国大人小孩哦中国北京南京"},flags, Version.LUCENE_36, new AnsjAnalysis(),true);
		for (TestDomain domain : page.getItems()) {
			System.out.println(domain.getContent());
		}
	}
	
}
