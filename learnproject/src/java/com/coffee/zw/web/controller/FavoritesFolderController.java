package com.coffee.zw.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coffee.zw.dao.FavoritesFolderDao;
import com.coffee.zw.domain.FavoritesFolder;
import com.coffee.zw.util.ContextUtils;


/**
 * @Title 控制类
 * @Author liuguangqiang
 * @CrTime 2012-8-3 上午10:10:47
 * @Version 1.0
 */
// 标注业务层组件
@Controller
@RequestMapping("/favorite.do")
public class FavoritesFolderController extends BaseController<FavoritesFolder, Long> {
	
	@Autowired
	FavoritesFolderDao ffd;
	
	/**
	 * 分页获取本收藏夹下所有的图片，
	 * 当folderid为空时则获取所有收藏
	 */
	//默认方法
	@RequestMapping
	public String getIndexInfo(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("1");
		FavoritesFolder  ff=new FavoritesFolder();
		ff.setId(2);
		ff.setFolderName("asdasda");
		ffd.initEntity(ff);
		System.out.println("2");
//		System.out.println(ffd.get(1L));
//		Query query=ffd.getSession().createSQLQuery("select * from favoritesfolder").addEntity(FavoritesFolder.class);
//		query.list();
//		System.out.println(query.list());
		return "index";
	}
	/**
	 * 添加
	 */
	@RequestMapping(params = "method=add")
	public void addFavorites( HttpServletRequest request,
			HttpServletResponse response) {
	}
}