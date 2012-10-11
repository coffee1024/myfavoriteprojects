package com.travel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 *
 * @author 
 *
 */
public class ConfigUtil {
	private static Logger logger=LoggerFactory.getLogger(ConfigUtil.class);
	private static Properties props = 	new Properties();
	private static ClassLoader loader = ConfigUtil.class.getClassLoader();
	static{
		init();
	}
	public static void init(){
		//ClassLoader.getResouceAsStream(): 
				InputStream ips = loader.getResourceAsStream("com/travel/sources/travel.properties");
				try {
					props.load(ips);
				} catch (IOException e) {
					logger.error("加载配置文件csipuser.properties失败:"+e.getMessage());
					e.printStackTrace();
				}
	}
	public static String get(String key){
		return props.getProperty(key);
	}
}
