package com.coffee.photo.utils.ftp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ftpserver.ftplet.DefaultFtplet;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpReply;
import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.ftplet.FtpletContext;
import org.apache.ftpserver.ftplet.FtpletResult;
import org.apache.ftpserver.ftplet.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.coffee.photo.service.account.UserService;
import com.coffee.photo.service.file.PhotoFileService;

public class FtpFtplet extends DefaultFtplet{

	@Autowired
	private UserService userService;
	@Autowired
	private PhotoFileService photoFileService;
	@Override
	public FtpletResult afterCommand(FtpSession session, FtpRequest request,
			FtpReply reply) throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.afterCommand(session, request, reply);
	}

	@Override
	public FtpletResult beforeCommand(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.beforeCommand(session, request);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init(FtpletContext ftpletContext) throws FtpException {
		// TODO Auto-generated method stub
		super.init(ftpletContext);
	}

	@Override
	public FtpletResult onAppendEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onAppendEnd(session, request);
	}

	@Override
	public FtpletResult onAppendStart(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onAppendStart(session, request);
	}

	@Override
	public FtpletResult onConnect(FtpSession session) throws FtpException,
			IOException {
		// TODO Auto-generated method stub
		return super.onConnect(session);
	}


	@Override
	public FtpletResult onDisconnect(FtpSession session) throws FtpException,
			IOException {
		// TODO Auto-generated method stub
		return super.onDisconnect(session);
	}


	@Override
	public FtpletResult onLogin(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onLogin(session, request);
	}


	@Override
	public FtpletResult onSite(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onSite(session, request);
	}

	@Override
	public FtpletResult onUploadEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
//		String path=session.getFileSystemView().getWorkingDirectory().getAbsolutePath();
//		String name=request.getArgument();
//		String root=session.getUser().getHomeDirectory();
//		String filePath="";
//		if (StringUtils.equals(path, "/")) {
//			filePath=root+File.separator+name;
//		}else{
//			filePath=root+StringUtils.replace(path, "/", File.separator)+File.separator+name;
//		}
//		User ftpUser=session.getUser();
//		com.coffee.photo.entity.account.User user=userService.findUserByLoginName(ftpUser.getName());
//		photoFileService.saveFtpFile(filePath, name, user);
		return super.onUploadEnd(session, request);
	}

	@Override
	public FtpletResult onUploadStart(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		String name=request.getArgument();
//		if (photoFileService.checkExt(name)) {
			return super.onUploadStart(session, request);
//		} else {
//			return FtpletResult.DISCONNECT;
//		}
	}

	@Override
	public FtpletResult onUploadUniqueEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		String path=session.getFileSystemView().getWorkingDirectory().getAbsolutePath();
		String name=request.getArgument();
		String root=session.getUser().getHomeDirectory();
		String filePath="";
		if (StringUtils.equals(path, "/")) {
			filePath=root+File.separator+name;
		}else{
			filePath=root+StringUtils.replace(path, "/", File.separator)+File.separator+name;
		}
		User ftpUser=session.getUser();
		com.coffee.photo.entity.account.User user=userService.findUserByLoginName(ftpUser.getName());
		photoFileService.saveFtpFile(filePath, name, user);
		return super.onUploadEnd(session, request);
	}

	@Override
	public FtpletResult onUploadUniqueStart(FtpSession session,
			FtpRequest request) throws FtpException, IOException {
		String name=request.getArgument();
		if (photoFileService.checkExt(name)) {
			return super.onUploadStart(session, request);
		} else {
			return FtpletResult.DISCONNECT;
		}
	}
	
}