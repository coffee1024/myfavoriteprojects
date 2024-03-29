package com.coffee.test;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coffee.dao.FavoritesFolderDao;
import com.coffee.domain.FavoritesFolder;
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
		FavoritesFolder  ff=new FavoritesFolder();
		ff.setContent("按时打算打算的");
		ffd.save(ff);
		System.out.println(ffd.get(3L).getContent());
//		System.out.println(ffd.get(id));
	}
	
}
