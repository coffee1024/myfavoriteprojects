package com.lgq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import weibo4j.util.WeiboConfig;



/**
 * 封装JDBC的基本操作
 *   Connection getConnection()
 *   void close(ResultSet,Statement,Connect)
 *   
 *   ResultSet query(String sql,Object... args)
 *   
 *   int update(String sql,Object... args)
 * @author liuguangqiang

 * @date:2011-12-14 上午11:30:26
 * @version :1.0
 *	访问数据库的支持类
 */

public class JdbcDaoSupport {
	protected Connection conn;
	protected PreparedStatement ps;

	
	/**
	 * 使用普通方法获取连接获取数据库连接
	 * @return Connection
	 */
	protected Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(WeiboConfig.getValue("dbdriver"));
			String url =WeiboConfig.getValue("dburl");
			String user=WeiboConfig.getValue("dbuser");
			String pwd=WeiboConfig.getValue("dbpassword");
			conn = DriverManager.getConnection(url,user,pwd);
		} catch (Exception e) {
		}
		return conn;
	}

	/**
	 * 释放资源
	 */
	protected void close(ResultSet rs,
			       PreparedStatement ps,Connection con){
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) conn.close();
		} catch (SQLException e) {
		}
	}
	/**
	 * 执行查询语句，返回ResultSet
	 * @param sql SELECT语句
	 * @return ResultSet
	 */
	 protected ResultSet query(String sql){
	    	ResultSet rs = null;
	    	conn=getConnection();    	
	    	try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
			} catch (SQLException e) {
			}
			return rs;    	
	    }
    protected ResultSet query(String sql,Object[] objs){
    	ResultSet rs = null;
    	conn=getConnection();    	
    	try {
			ps=conn.prepareStatement(sql);
			for (int i = 0; i < objs.length; i++) {
				ps.setObject(i+1, objs[i]);
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
		}
		return rs;    	
    }
    /**
     * 执行增删改的SQL语句
     * @param sql  insert,update,delete语句
     * @return 数据库受影响的行数
     */
    protected int update(String sql,Object[] objs){
    	conn = getConnection();
    	int ret=0;    	
    	try {
			ps=conn.prepareStatement(sql);
			for (int i = 0; i < objs.length; i++) {
				ps.setObject(i+1, objs[i]);
			}
			ret=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更新异常");
		}finally{
			close(null,ps,conn);//释放资源
		}
		return ret;   	
    }
}
