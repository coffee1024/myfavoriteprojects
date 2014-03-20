package com.coffee.photo.web.admin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coffee.photo.entity.account.User;
import com.coffee.photo.entity.file.PhotoFile;
import com.coffee.photo.entity.file.PhotoFile.Status;
import com.coffee.photo.service.account.UserService;
import com.coffee.photo.service.file.PhotoFileService;
import com.coffee.photo.utils.JsonMapper;
import com.coffee.photo.web.BaseController;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/admin/file")
public class PhotoFileAdminController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private PhotoFileService photoFileService;

	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model model) {
		buildPageRequest(request);
		Page<PhotoFile> page = photoFileService.getAll(pageRequest);
		model.addAttribute("page", page);
		return "admin/fileList";
	}

	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "enable/{id}")
	@ResponseBody
	public boolean enable(@PathVariable("id") Long id,Model model) {
		photoFileService.changeStatus(id, Status.CHECK_SUCCESS);
		return true;
	}
	@RequestMapping(value = "disable/{id}")
	@ResponseBody
	public boolean disable(@PathVariable("id") Long id,Model model) {
		photoFileService.changeStatus(id, Status.CHECK_FAIL);
		return true;
	}


	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		User user = userService.getUser(id);
		userService.deleteUser(id);
		redirectAttributes.addFlashAttribute("message", "删除用户" + user.getLoginName() + "成功");
		return "redirect:/admin/user";
	}

	/*
	 * 查询所有用户
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	@ResponseBody
	public String listUser(@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "userType", required = false) Integer userType,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "nickName", required = false) String nickName,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate,
			@RequestParam(value = "searchType", required = false) Integer searchType,
			@RequestParam(value = "status", required = false) Integer status, HttpServletRequest request,
			HttpServletResponse response) throws IOException, InstantiationException, IllegalAccessException {
		buildPageRequest(request);
		Page<User> userList = userService.search(loginName, userType, status, email, nickName, startDate, endDate,
				searchType, pageRequest);
		String str = JsonMapper.nonEmptyMapper().toJson(userList);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(str);
		return null;
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("user", userService.getUser(id));
		}
	}
}
