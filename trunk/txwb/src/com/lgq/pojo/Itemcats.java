package com.lgq.pojo;


public class Itemcats{
	private static final long serialVersionUID = 33230486742413888L;
	private int id;
	private String cid;
	private String name;
	private String parent_cid;
	private String is_parent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent_cid() {
		return parent_cid;
	}
	public void setParent_cid(String parent_cid) {
		this.parent_cid = parent_cid;
	}
	public String getIs_parent() {
		return is_parent;
	}
	public void setIs_parent(String is_parent) {
		this.is_parent = is_parent;
	}
	
	
}
