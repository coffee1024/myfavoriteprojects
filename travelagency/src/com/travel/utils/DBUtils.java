package com.travel.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 刘光强
 * @date 2012-4-1 下午3:37:13
 * @version 1.0
 **/
public class DBUtils {
	Logger logger=LoggerFactory.getLogger(DBUtils.class);
	private static String backupdir;
	private static String username;
	private static String password;
	static{
		init();
	}
	public static void init(){
		backupdir=ConfigUtil.get("dbbackdir");
		username=ConfigUtil.get("dbusername");
		password=ConfigUtil.get("dbpwd");
	}
	public static String backup() {
		try {
			Runtime rt = Runtime.getRuntime();
			if (!StringUtils.isEmpty(password)) {
				password=" -p"+password;
			}
			// 调用 mysql 的 cmd:
			Process child = rt.exec("mysqldump -u"+username+password+" travelmanager");// 设置导出编码为utf8。这里必须是utf8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
			InputStreamReader xx = new InputStreamReader(in, "utf8");// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码

			String inStr;
			// 要用来做导入用的sql目标文件：
			File file=new File(backupdir);
			if (!file.exists()) {
				file.mkdir();
			}
			file =new File(backupdir+"/"+DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd_HH.mm.ss")+".bak");
			FileOutputStream fout = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				StringBuffer sb = new StringBuffer("");
				sb.append(inStr + "\r\n");
				writer.write(sb.toString());
				// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
				writer.flush();
			}
			// 别忘记关闭输入输出流
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();
			return file.getPath();
		} catch (Exception e) {
			e.printStackTrace();
			return "备份失败";
		}
	}

	/**
	 * 导入
	 * 
	 */
	public static boolean load(String fPath) {
		try {
			Runtime rt = Runtime.getRuntime();

			// 调用 mysql 的 cmd:
			Process child = rt.exec("mysql -uroot travelmanager");
			OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fPath), "utf8"));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();

			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();
			// 别忘记关闭输入输出流
			out.close();
			br.close();
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
