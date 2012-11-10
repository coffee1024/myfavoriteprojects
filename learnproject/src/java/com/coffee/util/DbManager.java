package com.coffee.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * @Title 数据库管理
 * @Author ZhouQiang
 * @CrTime 2010-5-20 上午11:01:21
 * @Version 1.0
 */
public class DbManager {

	/**
	 * 安全关闭数据库资源的方法
	 * 
	 * @param conn
	 *            java.sql.Connection
	 * @param stmt
	 *            java.sql.Statement
	 * @param rs
	 *            java.sql.ResultSet
	 */
	public final static void closeDBResource(Connection conn, Statement stmt,
			ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {// ignore
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {// ignore
			}
		}
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {// ignore
			}
		}
	}
	
	/**
	 * 按配置表数据连接数据库
	 * @author PuTao
	 * @throws IOException 
	 * @date 2010-8-31
	 */
	public static Connection getConnection(Connection conn) throws IOException {
		String sConfigFile = "properties/default.properties";
		InputStream inputStream = ConfigConstant.class.getClassLoader()
				.getResourceAsStream(sConfigFile);
		Properties p = new Properties();
		try {
			p.load(inputStream);
			String DirverClass=p.getProperty("jdbc.driverClassName");
			String Url=p.getProperty("jdbc.url");
			String User=p.getProperty("jdbc.username");
			String Password=p.getProperty("jdbc.password");
			Class.forName(DirverClass);
			conn = DriverManager.getConnection(Url, User, Password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
