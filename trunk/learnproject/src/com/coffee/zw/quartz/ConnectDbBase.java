package com.coffee.zw.quartz;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffee.zw.util.ConfigConstant;

public class ConnectDbBase {
	/**
	 * 日志记录
	 */
	static Logger logger = LoggerFactory.getLogger(ConnectDbBase.class);
	
	/**
	 * 数据库连接配置
	 */
	String DirverClass="";
	String Url="";
	String User="";
	String Password="";
	protected Statement stmt;
	protected Connection conn=null;
	
	/**
	 * 连接数据库
	 * @author PuTao
	 * @date 2010-8-16
	 * @return
	 */
	protected Connection getConnection() {
		try {
			Class.forName(DirverClass);
			conn = DriverManager.getConnection(Url, User, Password);
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
		} catch (ClassNotFoundException e) {
			logger.error("can not find the class:"+e.getMessage(), e);
			e.printStackTrace();

		} catch (SQLException e) {
			logger.error("获取内网数据库连接出错:"+e.getMessage(), e);
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭数据库连接
	 * @author PuTao
	 * @date 2010-8-16
	 * @param rst
	 * @param conn
	 * @param stmt
	 */
	protected void closeConnection(Connection conn,Statement stmt){
		if (stmt != null){
			try{
				stmt.close();
			}
			catch(SQLException e){
				logger.error("close inner database PreparedStatement error:"+e.getMessage(), e);
				e.printStackTrace();
			}
		}
			
		if (conn!=null){
			try{
				conn.close();
			}
			catch(SQLException e){
				logger.error("close inner database connection error:"+e.getMessage(), e);
				e.printStackTrace();
			}
		}
	}
	
	protected void GetDbInfo(){
		String sConfigFile = "properties/default.properties";
		InputStream inputStream = ConfigConstant.class.getClassLoader()
				.getResourceAsStream(sConfigFile);
		Properties p = new Properties();
		try {
			p.load(inputStream);
			DirverClass=p.getProperty("jdbc.driverClassName");
			Url=p.getProperty("jdbc.url");
			User=p.getProperty("jdbc.username");
			Password=p.getProperty("jdbc.password");
		}catch(Exception e){
			logger.error("读取配置文件出错，无法得到配置信息中的缺省值；将使用系统默认值。", e);
		}
	}
}
