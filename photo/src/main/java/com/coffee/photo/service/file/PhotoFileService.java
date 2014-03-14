package com.coffee.photo.service.file;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coffee.photo.entity.file.PhotoFile;
import com.coffee.photo.repository.file.PhotoFileDao;
import com.coffee.photo.service.account.UserService;
import com.coffee.photo.service.account.ShiroDbRealm.ShiroUser;
import com.coffee.photo.utils.FileUtils;
import com.coffee.photo.utils.ImageUtils;

@Component
@Transactional
public class PhotoFileService {
	@Value("${photo.store.originalphoto}")
	private  String originalFilePath;
	
	@Autowired
	private PhotoFileDao photoFileDao;
	@Autowired
	private UserService userService;
	
	public void saveFile(HttpServletRequest request) {
		
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");
		List fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException ex) {
			ex.printStackTrace();
			return;
		}
		for (Object object : fileList) {
			PhotoFile photoFile=new PhotoFile();
			// if (object instanceof FileItem) {
			FileItem item = (FileItem) object;
			if (item.isFormField()) {
				continue;
			}
			String fileName=item.getName();
			if (StringUtils.isBlank(fileName)) {
				continue;
			}
			long size = item.getSize();
			String savePath=getFilePath(originalFilePath, fileName);
			File saveFile=null;
			do {
				FileUtils.makeDirectory(savePath);
				saveFile = new File(savePath);
			} while (saveFile.exists());
			try {
				item.write(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			Map<String, Integer> map=ImageUtils.getPictureSize(savePath);
			if (map!=null) {
				photoFile.setHeight(map.get("height"));
				photoFile.setWidth(map.get("width"));
			}
			photoFile.setCreateDate(new Date());
			ShiroUser user=userService.getCurrentUser();
			photoFile.setCreateUserId(user.id);
			photoFile.setCreateUserLoginName(user.loginName);
			photoFile.setCreateUserNickName(user.nickName);
			photoFile.setSourceFileLength(size);
			photoFile.setSourceFileName(fileName);
			photoFile.setSourceFilePath(savePath);
			photoFileDao.save(photoFile);
		}
	}

	/**
	 * 获取文件名2013/12/01/XXXXX.XX
	 */
	private String getFilePath(String rootPath,String fileName){

		Date dt = new Date();
		/*
		 * String sResult = DateUtils.format(dt, "yyyyMMddHHmmss") + dt.getTime()
		 * + stp;
		 */
		// 修改为在年月日时分时候加入_符号
		// logger.debug("dttime is:"+dt.getTime());
		StringUtils.substringAfterLast(fileName, ".");
		String sResult = rootPath+File.separator+ DateFormatUtils.format(dt, "yyyy"+File.separator+"MM"+File.separator+"dd"+File.separator+"HH") +File.separator+ dt.getTime();
		return sResult;
	}
}
