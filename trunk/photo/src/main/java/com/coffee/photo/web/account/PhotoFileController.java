package com.coffee.photo.web.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coffee.photo.service.file.PhotoFileService;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 
 * @author liuguangqiang
 */
@Controller
@RequestMapping(value = "/upload")
public class PhotoFileController {
	@Autowired
	private PhotoFileService photoFileService;

	@RequestMapping(method = RequestMethod.GET)
	public String uploadJump(HttpServletRequest request,HttpServletResponse response) {
		return "upload/upload";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String upload(HttpServletRequest request,HttpServletResponse response) {
		photoFileService.saveFile(request);
		return "account/login";
	}

}
