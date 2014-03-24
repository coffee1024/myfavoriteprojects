package com.coffee.photo.service.file;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ftpserver.ftplet.FtpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coffee.photo.entity.account.User;
import com.coffee.photo.entity.file.PhotoFile;
import com.coffee.photo.entity.file.PhotoFile.Status;
import com.coffee.photo.repository.file.PhotoFileDao;
import com.coffee.photo.service.account.UserService;
import com.coffee.photo.service.account.ShiroDbRealm.ShiroUser;
import com.coffee.photo.utils.FileUtils;
import com.coffee.photo.utils.ImageUtils;
import com.google.common.collect.Lists;

@Component
@Transactional
public class PhotoFileService {
	@Value("${photo.store.originalphoto}")
	private  String originalFilePath;
	@Value("${photo.upload.enablext}")
	private  String enableExt;
	
	@Autowired
	private PhotoFileDao photoFileDao;
	@Autowired
	private UserService userService;
	
	private static Logger logger = LoggerFactory.getLogger(PhotoFileService.class);
	
	public void saveHttpFile(HttpServletRequest request) {
		
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");
		List<FileItem> fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException ex) {
			ex.printStackTrace();
			return;
		}
		for (FileItem item : fileList) {
			PhotoFile photoFile=new PhotoFile();
			// if (object instanceof FileItem) {
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
			ShiroUser shiroUser=userService.getCurrentUser();
			User user=userService.getUser(shiroUser.id);
			photoFile.setCreateUserId(shiroUser.id);
			photoFile.setCreateUserLoginName(shiroUser.loginName);
			photoFile.setCreateUserNickName(shiroUser.nickName);
			photoFile.setSourceFileLength(size);
			photoFile.setSourceFileName(fileName);
			photoFile.setSourceFilePath(savePath);
			photoFile.setUploadType(0);
			photoFile.setStatus(Status.UPLOAD_SUCCESS);
			photoFileDao.save(photoFile);
			user.setUploadNum(user.getUploadNum()+1);
			userService.updateUser(user);
			logger.info("{0} save http file {1}",user.getLoginName(),photoFile.getSourceFilePath());
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
		String sResult = rootPath+File.separator+ DateFormatUtils.format(dt, "yyyy"+File.separator+"MM"+File.separator+"dd"+File.separator+"HH") +File.separator+ dt.getTime()+"."+StringUtils.substringAfterLast(fileName, ".");
		return sResult;
	}
	public void saveFtpFile(String filePath,String fileName,User user) throws FtpException {
			
			File file=new File(filePath);
			if (file.exists()&&user!=null) {
				PhotoFile photoFile=new PhotoFile();
				Map<String, Integer> map=ImageUtils.getPictureSize(filePath);
				if (map!=null) {
					photoFile.setHeight(map.get("height"));
					photoFile.setWidth(map.get("width"));
				}
				photoFile.setCreateDate(new Date());
				photoFile.setCreateUserId(user.getId());
				photoFile.setCreateUserLoginName(user.getLoginName());
				photoFile.setCreateUserNickName(user.getNickName());
				photoFile.setSourceFileLength(file.length());
				photoFile.setSourceFileName(fileName);
				photoFile.setSourceFilePath(filePath);
				photoFile.setUploadType(1);
				photoFile.setStatus(Status.UPLOAD_SUCCESS);
				photoFileDao.save(photoFile);
				user.setUploadNum(user.getUploadNum()+1);
				userService.updateUser(user);
				logger.info("{0} save ftp file {1}",user.getLoginName(),photoFile.getSourceFilePath());
			}else{
				throw new FtpException("ftp上传入库失败");
			}
		}
	
	public boolean deleteFile(Long id){
		ShiroUser shiroUser=userService.getCurrentUser();
		User user=userService.getUser(shiroUser.id);
		Long uploadNum=user.getUploadNum();
		if (uploadNum!=null&&uploadNum>0) {
			user.setUploadNum(user.getUploadNum()-1);
			userService.updateUser(user);
		}
		return true;
	}
	public PhotoFile get(Long id){
		return photoFileDao.findOne(id);
	}
	public PhotoFile save(PhotoFile photoFile){
		return photoFileDao.save(photoFile);
	}
	public PhotoFile changeStatus(Long id,Status status){
		PhotoFile file=photoFileDao.findOne(id);
		file.setStatus(status);
		
		return photoFileDao.save(file);
	}
	public Page<PhotoFile> getAll(Pageable pageRequest) {
		Page<PhotoFile> page = photoFileDao.findAll(pageRequest);
		return page;
	}
	public Page<PhotoFile> search(String title, String memo, String createUserLoginName, String createUserNickName, Integer type,
			Date startDate, Date endDate, Integer status,Integer uploadType,Integer searchType, Pageable pageRequest) {
		Page<PhotoFile> photoFileList = photoFileDao.findAll(getSpec(title, memo, createUserLoginName,  createUserNickName,  type,
				startDate,endDate, status,uploadType,searchType), pageRequest);
		return photoFileList;
	}

	public Specification<PhotoFile> getSpec(final String title, final String memo, final String createUserLoginName,final  String createUserNickName,final  Integer type,
			final Date startDate,final  Date endDate,final  Integer status,final Integer uploadType,final Integer searchType) {
		return new Specification<PhotoFile>() {
			@Override
			public Predicate toPredicate(Root<PhotoFile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = Lists.newArrayList();
				Path<String> createUserLoginNamePath = root.get("createUserLoginName");
				Path<Integer> typePath = root.get("type");
				Path<Integer> uploadTypePath = root.get("uploadType");
				Path<String> titlePath = root.get("title");
				Path<String> memoPath = root.get("memo");
				Path<Integer> statusPath = root.get("status");
				Path<String> createUserNickNamePath = root.get("createUserNickName");
				Path<Date> createDatePath = root.get("createDate");
				if (StringUtils.isNotBlank(createUserLoginName)) {
					if ((searchType != null) && (searchType.intValue() == 0)) {
						predicates.add(cb.equal(createUserLoginNamePath, createUserLoginName));
					} else {
						predicates.add(cb.like(createUserLoginNamePath, "%" + createUserLoginName + "%"));
					}
				}
				if (StringUtils.isNotBlank(title)) {
					if ((searchType != null) && (searchType.intValue() == 0)) {
						predicates.add(cb.equal(titlePath, title));
					} else {
						predicates.add(cb.like(titlePath, "%" + title + "%"));
					}
				}
				if (StringUtils.isNotBlank(memo)) {
					if ((searchType != null) && (searchType.intValue() == 0)) {
						predicates.add(cb.equal(memoPath, memo));
					} else {
						predicates.add(cb.like(memoPath, "%" + memo + "%"));
					}
				}
				if (uploadType != null) {
					predicates.add(cb.equal(uploadTypePath, uploadType));
				}
				if (type != null) {
					predicates.add(cb.equal(typePath, type));
				}
				if (status != null) {
					predicates.add(cb.equal(statusPath, status));
				}
				if (StringUtils.isNotBlank(createUserNickName)) {
					if ((searchType != null) && (searchType.intValue() == 0)) {
						predicates.add(cb.equal(createUserNickNamePath, createUserNickName));
					} else {
						predicates.add(cb.like(createUserNickNamePath, "%" + createUserNickName + "%"));
					}
				}
				
				if (startDate != null) {
					predicates.add(cb.greaterThanOrEqualTo(createDatePath, startDate));
				}
				if (endDate != null) {
					predicates.add(cb.lessThanOrEqualTo(createDatePath, endDate));
				}

				Predicate[] arr = predicates.toArray(new Predicate[predicates.size()]);
				return query.where(arr).getRestriction();
			}
		};
	}
	public boolean checkExt(String fileName){
		String ext=fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		if (StringUtils.isNotBlank(enableExt)&&StringUtils.isNotBlank(ext)) {
			return StringUtils.contains(enableExt, ext);
		}
		return false;
	}
}
