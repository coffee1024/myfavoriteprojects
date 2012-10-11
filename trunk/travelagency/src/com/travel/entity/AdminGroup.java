package com.travel.entity;
/**
 *@author  刘光强
 *@date    2012-3-28 下午9:14:10
 *@version 1.0
 **/
public class AdminGroup {
	private Integer id;
	private String groupName;
	private Integer level;
	private String desc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
