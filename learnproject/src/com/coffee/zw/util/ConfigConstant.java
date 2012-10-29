package com.coffee.zw.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

/**
 * @Title 系统配置常量
 * @Author ZhouQiang
 * @CrTime 2010-5-20 上午09:37:51
 * @Version 1.0
 */
public class ConfigConstant {
	/**
	 * 日志记录
	 */
	static Logger logger = LoggerFactory.getLogger(ConfigConstant.class);
	/**
	 * 存放配置信息的哈希表
	 */
	static HashMap<String, String> map = new HashMap<String, String>();

	/**
	 * 查询配置信息的sql语句
	 */
	static String Query_Sql = "SELECT CONFIG_NAME,SITE_ID,CONFIG_VALUE FROM CONFIG";

	/**
	 * 缺省站点值
	 */
	static String Default_Site = "1";
	
	/**
	 * 缺省搜索方式
	 */
	static String Default_Search_Type = "jdbc";

	/**
	 * 获取系统的缺省站点值
	 */
	public static String getDefaultSite() {
		return Default_Site;
	}

	private static void setDefaultSite(String defaultSite) {
		Default_Site = defaultSite;
	}

	/**
	 * 获取系统的搜索方式，jdbc表示关系数据库检索；trs表示trs检索
	 */
	public static String getDefaultSearchType() {
		return Default_Search_Type;
	}

	private static void setDefaultSearchType(String defaultSearchType) {
		Default_Search_Type = defaultSearchType;
	}

	static {
		reload();
		getCfgInfo();
	}

	/**
	 * 加载系统配置信息的主函数
	 */
//	public static synchronized void reload() {
//		HashMap<String, String> temp = new HashMap<String, String>();
//		ApplicationContext ctx = new ClassPathXmlApplicationContext(
//				"applicationContext.xml");
//
//		// 1.使用已有的datasource获取连接，执行sql，获取信息
//		DataSource ds = (DataSource) ctx.getBean("dataSource");
//		if (ds != null) {
//			Connection conn = null;
//			PreparedStatement ps = null;
//			ResultSet rs = null;
//			try {
//				conn = DataSourceUtils.getConnection(ds);
//				ps = conn.prepareStatement(Query_Sql);
//				rs = ps.executeQuery();
//				while (rs.next()) {
//					temp.put(rs.getString(1) + "--" + rs.getString(2), rs
//							.getString(3));
//				}
//			} catch (Exception e) {
//				logger.error("加载系统配置信息出现错误。", e);
//			} finally {
//				DbManager.closeDBResource(conn, ps, rs);
//			}
//		}
//		map.clear();
//		map = temp;
//	}
	
	/**
	 * 加载系统配置信息的主函数
	 */
	public static synchronized void reload() {
		HashMap<String, String> temp = new HashMap<String, String>();
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = com.coffee.zw.util.DbManager.getConnection(conn);
//			ps = conn.prepareStatement(Query_Sql);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				temp.put(rs.getString(1) + "--" + rs.getString(2), rs
//						.getString(3));
//			}
//		} catch (Exception e) {
//			logger.error("加载系统配置信息出现错误。", e);
//		} finally {
//			DbManager.closeDBResource(conn, ps, rs);
//		}
		map.clear();
		map = temp;
	}
	

	/**
	 * 根据配置名称和站点Id取得配置信息
	 * 
	 * @param cfgName
	 *            配置名称
	 * @param siteId
	 *            站点Id
	 */
	public static String getValue(String cfgName, String siteId) {
		if (null == map.get(cfgName + "--" + siteId)) {
			return "";
		}
		return (String) map.get(cfgName + "--" + siteId);
	}

	/**
	 * 根据配置名称和站点Id取得配置信息
	 * 
	 * @param cfgName
	 *            配置名称
	 * @param siteId
	 *            站点Id
	 */
	public static String getValue(String cfgName, Long siteId) {
		return (String) map.get(cfgName + "--" + siteId);
	}

	public static void main(String[] args) {
		System.out.println(map);
		System.out.println(getValue("PATH_WATERMARK", 1L));
		// getCfgInfo2();
		getCfgInfo();
		System.out.println(getDefaultSite());
		System.out.println(getDefaultSearchType());
	}

	/**
	 * 读取配置文件的方法一
	 */
	static void getCfgInfo2() {
		String file = "applicationContext.xml";
		file = "hibernate/test.xml";
		try {
			XMLConfiguration config = new XMLConfiguration(file);
			System.out.println(config.getString("description"));
			System.out.println(config.getString("site.id"));
		} catch (ConfigurationException e) {
			logger.error("读取配置文件出错，无法得到配置信息中的缺省值。");
		}

	}

	/**
	 * 读取配置文件的方法二
	 */
	static void getCfgInfo() {
		String sConfigFile = "properties/default.properties";
		InputStream inputStream = ConfigConstant.class.getClassLoader()
				.getResourceAsStream(sConfigFile);
		Properties p = new Properties();
		try {
			p.load(inputStream);
			String site = p.getProperty("default.site.id");
			String searchType = p.getProperty("default.search.type");
			if (StringUtils.isNotBlank(site))
				setDefaultSite(site);
			if (StringUtils.isNotBlank(searchType))
				setDefaultSearchType(searchType);
		} catch (IOException e) {
			logger.error("读取配置文件出错，无法得到配置信息中的缺省值；将使用系统默认值。", e);
		}
	}
}
