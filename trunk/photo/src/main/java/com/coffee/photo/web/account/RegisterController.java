package com.coffee.photo.web.account;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.ftpserver.usermanager.UserFactory;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coffee.photo.entity.account.User;
import com.coffee.photo.service.account.UserService;
import com.coffee.photo.utils.ftp.FtpServerListener;

/**
 * 用户注册的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String registerForm() {
		return "account/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid User user, RedirectAttributes redirectAttributes,HttpServletRequest request) throws FtpException {
		userService.registerUser(user);
		 DefaultFtpServer server = (DefaultFtpServer) request.getServletContext().getAttribute(FtpServerListener.FTPSERVER_CONTEXT_NAME);
		         if(server!=null){
		             UserManager um = server.getUserManager();
		             if(um.doesExist(user.getLoginName())){
		                 um.delete(user.getLoginName());
		             }
		             UserFactory userFact = new UserFactory();
		             userFact.setName(user.getLoginName());
		             userFact.setPassword(user.getPlainPassword());
		             userFact.setHomeDirectory("D:\\tmp\\"+user.getLoginName());
		             userFact.setMaxIdleTime(600000);//10分钟无操作自动断开连接
		             List<Authority> alist = new ArrayList<Authority>();
		             Authority a = new WritePermission();//写权限
		             alist.add(a);
		             userFact.setAuthorities(alist);
		             org.apache.ftpserver.ftplet.User ftpUser = userFact.createUser();
		             um.save(ftpUser);
		         }

		redirectAttributes.addFlashAttribute("username", user.getLoginName());
		return "redirect:/login";
	}

	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("loginName") String loginName) {
		if (userService.findUserByLoginName(loginName) == null) {
			return "true";
		} else {
			return "false";
		}
	}
}
