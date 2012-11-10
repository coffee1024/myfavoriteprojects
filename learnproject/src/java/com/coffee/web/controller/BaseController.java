package com.coffee.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffee.core.orm.Page;

/**
 * @Title 基本分页对象控制类
 * @CrTime 2010-4-19 上午09:51:10
 * @Version 1.0
 */
public class BaseController<T, PK> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 默认的分页页面的大小（12）
	 */
	protected final int Page_Size = 12;
	/**
	 * 分页对象
	 */
	protected Page<T> page = new Page<T>(Page_Size);
	/**
	 * 保留对象，用作扩展
	 */
	protected T entity;
	/**
	 * 保留对象的主键，用作扩展
	 */
	protected PK id;

	public void setId(PK id) {
		this.id = id;
	}

	protected Page<T> getPage() {
		return page;
	}

	/**
	 * 根据http请求设置分页参数
	 * 
	 * @param request
	 *            http请求
	 */
	protected void setPageInfo(HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		String orderBy = request.getParameter("orderBy");
		String order = request.getParameter("order");

		try {
			if (StringUtils.isNotBlank(pageNo)) {
				page.setPageNo(Integer.parseInt(pageNo));
			} else {
				// 设置默认页号
				page.setPageNo(1);
			}
			if (StringUtils.isNotBlank(pageSize)) {
				page.setPageSize(Integer.parseInt(pageSize));
			} else {
				page.setPageSize(this.Page_Size);
			}
			if (StringUtils.isNotBlank(orderBy)) {
				page.setOrderBy(orderBy);// lowercase!
			} else {
				// page.setOrderBy("picture_id");
			}
			if (StringUtils.isNotBlank(order)) {
				page.setOrder(order);// ASC/DESC
			} else {
				page.setOrder("desc");
			}
		} catch (Exception e) {
			logger.error("设置分页参数失败：", e.getMessage());
		}
	}

	/**
	 * 根据http请求设置分页参数
	 * 
	 * @param req
	 *            http请求
	 * @param pg
	 *            翻页对象
	 */
	protected void setPageInfo(HttpServletRequest req, Page<T> pg) {
		String pageNo = req.getParameter("pageSingleNo");
		String pageSize = req.getParameter("singlePageSize");
		String orderBy = req.getParameter("orderBy");
		String order = req.getParameter("order");

		try {
			if (StringUtils.isNotBlank(pageNo)) {
				pg.setPageNo(Integer.parseInt(pageNo));
			} else {
				// 设置默认页号
				pg.setPageNo(1);
			}
			if (StringUtils.isNotBlank(pageSize)) {
				pg.setPageSize(Integer.parseInt(pageSize));
			} else {
				pg.setPageSize(this.Page_Size);
			}
			if (StringUtils.isNotBlank(orderBy)) {
				pg.setOrderBy(orderBy);// lowercase!
			} else {
				// page.setOrderBy("picture_id");
			}
			if (StringUtils.isNotBlank(order)) {
				pg.setOrder(order);
			} else {
				pg.setOrder("desc");
			}
		} catch (Exception e) {
			logger.error("设置分页参数失败：", e.getMessage());
		}
	}
	/**
	 * 根据http请求设置分页参数
	 * 
	 * @param req
	 *            http请求
	 * @param pg
	 *            翻页对象
	 */
	protected void setPageInfoTest(HttpServletRequest req, Page<T> pg) {
		String pageNo = req.getParameter("pageNo");
		String pageSize = req.getParameter("pageSize");
		String orderBy = req.getParameter("orderBy");
		String order = req.getParameter("order");
		try {
			if (StringUtils.isNotBlank(pageNo)) {
				pg.setPageNo(Integer.parseInt(pageNo));
			} else {
				// 设置默认页号
				pg.setPageNo(1);
			}
			if (StringUtils.isNotBlank(pageSize)) {
				pg.setPageSize(Integer.parseInt(pageSize));
			} else {
				pg.setPageSize(this.Page_Size);
			}
			if (StringUtils.isNotBlank(orderBy)) {
				pg.setOrderBy(orderBy);// lowercase!
			} else {
				// page.setOrderBy("picture_id");
			}
			if (StringUtils.isNotBlank(order)) {
				pg.setOrder(order);
			} else {
				pg.setOrder("desc");
			}
		} catch (Exception e) {
			logger.error("设置分页参数失败：", e.getMessage());
		}
	}
}
