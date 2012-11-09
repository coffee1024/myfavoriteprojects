package com.coffee.zw.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.coffee.zw.core.orm.Page;
import com.coffee.zw.core.orm.hibernate.EntityManager;
import com.coffee.zw.dao.FavoritesFolderDao;
import com.coffee.zw.domain.FavoritesFolder;
import com.coffee.zw.util.ConfigConstant;
import com.coffee.zw.util.ContextUtils;
import com.coffee.zw.util.StringUtils;
/**
 * @Title 收藏夹管理类. 实现收藏夹的所有业务管理函数. 演示派生DAO层子类的模式,由注入的UserDao封装数据库访问.
 *        通过泛型声明继承EntityManager,默认拥有CRUD管理方法. 使用Spring annotation定义事务管理.
 * 
 * @Author dulei
 * @CreateTime 2012-11-8
 * @Version 3.0
 */
@Service
@Transactional
public class FavoritesFolderManager extends EntityManager<FavoritesFolder, Long> {

	@Autowired
	private FavoritesFolderDao ffd;
	
	@Override
	protected FavoritesFolderDao getEntityDao() {
		return ffd;
	}
	
}