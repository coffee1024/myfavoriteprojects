package com.coffee.photo.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected final String PAGE_SIZE = "10";
	protected Pageable pageRequest;

	public void buildPageRequest(HttpServletRequest request) {
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("limit");
		String orderBy = request.getParameter("orderBy");
		String order = request.getParameter("order");
		try {
			if (StringUtils.isBlank(pageNo)) {
				// 设置默认页号
				pageNo = "1";
			}
			if (StringUtils.isBlank(pageSize)) {
				pageSize = PAGE_SIZE;
			}
			Sort sort = null;
			if (StringUtils.isBlank(orderBy)) {
				orderBy = "id";
			}
			if (StringUtils.isBlank(order) || order.toLowerCase().equals("desc")) {
				sort = new Sort(Direction.DESC, orderBy);
			} else if (order.toLowerCase().equals("asc")) {
				sort = new Sort(Direction.ASC, orderBy);
			}
			pageRequest = new PageRequest(Integer.parseInt(pageNo) - 1, Integer.parseInt(pageSize), sort);
		} catch (Exception e) {
			logger.error("设置分页参数失败：", e.getMessage());
			pageRequest = new PageRequest(0, Integer.parseInt(PAGE_SIZE));
		}
	}
}
