package com.coffee.photo.entity.file;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.coffee.photo.entity.IdEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "p_photo_file")
public class PhotoFile extends IdEntity {
	/**
	 * 
	 */
	private String title;
	private String memo;
	/**
	 * 上传的文件名
	 */
	private String sourceFileName;
	private String sourceFilePath;
	private Long sourceFileLength;
	private String thumbnailFilePath;
	private Integer width;
	private Integer height;
	private Date createDate;
	private Long createUserId;
	private String createUserLoginName;
	private String createUserNickName;
	private Integer type;
	private Integer recommend;
	private String extName;
	private Boolean isOriginal;
	private Integer status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	public Long getSourceFileLength() {
		return sourceFileLength;
	}

	public void setSourceFileLength(Long sourceFileLength) {
		this.sourceFileLength = sourceFileLength;
	}

	public String getThumbnailFilePath() {
		return thumbnailFilePath;
	}

	public void setThumbnailFilePath(String thumbnailFilePath) {
		this.thumbnailFilePath = thumbnailFilePath;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	
	public Boolean getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(Boolean isOriginal) {
		this.isOriginal = isOriginal;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserLoginName() {
		return createUserLoginName;
	}

	public void setCreateUserLoginName(String createUserLoginName) {
		this.createUserLoginName = createUserLoginName;
	}

	public String getCreateUserNickName() {
		return createUserNickName;
	}

	public void setCreateUserNickName(String createUserNickName) {
		this.createUserNickName = createUserNickName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}