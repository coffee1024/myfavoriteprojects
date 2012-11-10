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
import com.coffee.dao.FavoritesFolderDao;
import com.coffee.domain.TestDomain;
import com.coffee.service.FavoritesFolderManager;

/**
 *@author  liuguangqiang
 *@date    2012-10-24 上午08:29:54
 *@version 1.0
 **/
public class JUnitDaoTest extends AbstractBaseTestCase {
	FavoritesFolderManager ffd;
	/**
	 * 使用spring的配置获取相关的类
	 */
	@Before
	public void strat(){
		ffd=fscontext.getBean(FavoritesFolderManager.class);
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
//		FavoritesFolder  ff=new FavoritesFolder();
//		ff.setContent("中华人民共和国你好大中国北京南京");
//		ffd.saveFullText(ff);
		BooleanClause.Occur[] flags=new BooleanClause.Occur[]{BooleanClause.Occur.SHOULD};
		System.out.println(ffd.fullTextQuary("中华人民共和国"));
		System.out.println(ffd.fullTextPageQuary(new Page<TestDomain>(2), new String[]{"content"}, new String[]{"中华人民共和国"},flags, Version.LUCENE_36, new AnsjAnalysis()).getItems());
	}
	
}
