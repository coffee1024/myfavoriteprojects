package com.travel.entity;

import java.sql.Timestamp;

/**
 * @ author   刘光强
 * @ date     2012-3-19 下午1:47:47
 * @ version  1.0
 **/
public class TravelAgency {
	private Integer id;
	private String name;
	private Timestamp createTime;
	private String address;
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
