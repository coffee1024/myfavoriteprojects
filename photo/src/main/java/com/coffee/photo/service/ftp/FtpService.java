package com.coffee.photo.service.ftp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.ftpserver.usermanager.UserFactory;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.coffee.photo.entity.account.User;
import com.coffee.photo.utils.ftp.FtpServerListener;

@Component
public class FtpService {
	@Value("${photo.ftp.rootpath}")
	private  String ftpRoot;
	@Value("${photo.ftp.maxidletime}")
	private  Integer maxIdleTime;
	
	private static Logger logger=LoggerFactory.getLogger(FtpService.class);
	
	public boolean registerFtpUser(HttpServletRequest request,User user) throws FtpException{
			DefaultFtpServer server = (DefaultFtpServer) request.getServletContext().getAttribute(FtpServerListener.FTPSERVER_CONTEXT_NAME);
			if(server!=null){
				UserManager um = server.getUserManager();
				if(um.doesExist(user.getLoginName())){
					um.delete(user.getLoginName());
				}
				UserFactory userFact = new UserFactory();
				userFact.setName(user.getLoginName());
				userFact.setPassword(user.getPlainPassword());
				userFact.setHomeDirectory(ftpRoot+File.separator+user.getLoginName());
				userFact.setMaxIdleTime(maxIdleTime);//10分钟无操作自动断开连接
				List<Authority> alist = new ArrayList<Authority>();
				Authority a = new WritePermission();//写权限
				alist.add(a);
				userFact.setAuthorities(alist);
				org.apache.ftpserver.ftplet.User ftpUser = userFact.createUser();
				um.save(ftpUser);
				logger.info("register ftp user: {0}.",ftpUser.getName());
			}else{
				return false;
			}
		return true;
	}
	public boolean deleteFtpUser(HttpServletRequest request,User user){
		DefaultFtpServer server = (DefaultFtpServer) request.getServletContext().getAttribute(FtpServerListener.FTPSERVER_CONTEXT_NAME);
		if(server!=null){
			UserManager um = server.getUserManager();
			try {
				um.delete(user.getLoginName());
			} catch (Exception e) {
				return false;
			}
			return true;
		}else{
			return false;
		}
	}
	public boolean deleteFtpUser(HttpServletRequest request,String loginName){
		DefaultFtpServer server = (DefaultFtpServer) request.getServletContext().getAttribute(FtpServerListener.FTPSERVER_CONTEXT_NAME);
		if(server!=null){
			UserManager um = server.getUserManager();
			try {
				um.delete(loginName);
			} catch (Exception e) {
				return false;
			}
			return true;
		}else{
			return false;
		}
	}
}
