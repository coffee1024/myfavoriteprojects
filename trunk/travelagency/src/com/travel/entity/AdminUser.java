package com.travel.entity;

import java.sql.Timestamp;

/**
 * @ author   刘光强
 * @ date     2012-3-19 下午1:47:47
 * @ version  1.0
 **/
public class AdminUser {
	private Integer id;
	private String userName;
	private String password;
	private String phone;
	private Integer groupId;
	private String lastIP;
	private Timestamp lastTime;
	private String description;
	//many to one
	private AdminGroup adminGroup;
	
	
	public AdminGroup getAdminGroup() {
		return adminGroup;
	}
	public void setAdminGroup(AdminGroup adminGroup) {
		this.adminGroup = adminGroup;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getLastIP() {
		return lastIP;
	}
	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "用户名："+userName;
	}
	@Override
	public boolean equals(Object obj) {
		AdminUser adminUser=(AdminUser)obj;
		if (this.id==adminUser.getId()&&this.userName.equals(adminUser.getUserName())&&this.groupId==adminUser.getGroupId()&&this.description.equals(adminUser.getDescription())) {
			return true;
		}
		return false;
	}
	
	
}
