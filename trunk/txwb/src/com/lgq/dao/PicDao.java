package com.lgq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *@author  Administrator
 *@date    2012-6-14 下午5:13:45
 *@version 1.0
 **/
public class PicDao extends JdbcDaoSupport {
	
	public String getString(int id){
		String sql="select * from picname where id="+id;
		ResultSet rs=super.query(sql);
		String str="";
		try {
			rs.next();
			str= rs.getString("name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.close(rs, ps, null);
		return str;
	}
	public  int getCount(){
		String sql="select count(*) from picname";
		ResultSet rs=super.query(sql);
		int i=0;
		try {
			rs.next();
			i= rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.close(rs, ps, null);
		return i;
	}
	public void add(String str){
		String sql="insert into picname values(null,?)";
		super.update(sql, new Object[]{str});
	}
}
