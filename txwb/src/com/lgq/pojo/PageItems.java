package com.lgq.pojo;

import java.util.List;

/**
 *@author  liuguangqiang
 *@date    2012-7-13 下午3:23:01
 *@version 1.0
 **/
public class PageItems {
	private int pageno=1;
	private int pagesize=20;
	private int totalpage;
	private int totalcount;
	private List<Item> items;
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.pageno+"--"+this.pagesize+"--"+this.totalpage+"--"+this.totalcount;
	}
}
