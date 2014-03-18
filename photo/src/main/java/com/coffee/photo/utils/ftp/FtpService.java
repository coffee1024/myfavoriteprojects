package com.coffee.photo.utils.ftp;

import java.io.File;
import java.io.IOException;

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

public class FtpService extends DefaultFtplet{

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
	public FtpletResult onDeleteEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onDeleteEnd(session, request);
	}

	@Override
	public FtpletResult onDeleteStart(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onDeleteStart(session, request);
	}

	@Override
	public FtpletResult onDisconnect(FtpSession session) throws FtpException,
			IOException {
		// TODO Auto-generated method stub
		return super.onDisconnect(session);
	}

	@Override
	public FtpletResult onDownloadEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onDownloadEnd(session, request);
	}

	@Override
	public FtpletResult onDownloadStart(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onDownloadStart(session, request);
	}

	@Override
	public FtpletResult onLogin(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onLogin(session, request);
	}

	@Override
	public FtpletResult onMkdirEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onMkdirEnd(session, request);
	}

	@Override
	public FtpletResult onMkdirStart(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onMkdirStart(session, request);
	}

	@Override
	public FtpletResult onRenameEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onRenameEnd(session, request);
	}

	@Override
	public FtpletResult onRenameStart(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onRenameStart(session, request);
	}

	@Override
	public FtpletResult onRmdirEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onRmdirEnd(session, request);
	}

	@Override
	public FtpletResult onRmdirStart(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onRmdirStart(session, request);
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
		// TODO Auto-generated method stub
		String path=session.getFileSystemView().getWorkingDirectory().getAbsolutePath();
		String name=request.getArgument();
		String root=session.getUser().getHomeDirectory();
		String filePath=root+File.separator+path+File.separator+name;
		User ftpUser=session.getUser();
		com.coffee.photo.entity.account.User user=userService.findUserByLoginName(ftpUser.getName());
		photoFileService.saveFtpFile(filePath, name, user);
		return super.onUploadEnd(session, request);
	}

	@Override
	public FtpletResult onUploadStart(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
//		throw new FtpException("禁止上传");
		String name=request.getArgument();
		if (photoFileService.checkExt(name)) {
			return super.onUploadStart(session, request);
		} else {
			return FtpletResult.DISCONNECT;
		}
	}

	@Override
	public FtpletResult onUploadUniqueEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onUploadUniqueEnd(session, request);
	}

	@Override
	public FtpletResult onUploadUniqueStart(FtpSession session,
			FtpRequest request) throws FtpException, IOException {
		// TODO Auto-generated method stub
		return super.onUploadUniqueStart(session, request);
	}
	
}