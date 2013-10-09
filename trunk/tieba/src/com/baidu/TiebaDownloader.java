package com.baidu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import  com.baidu.beans.*;
import com.baidu.utils.*;

public class TiebaDownloader implements Runnable {
	String dPage=null;
	String path=null;
	static ExecutorService pool=null;
	static Connection connection = null;
	static CountDownLatch latch=null;;  

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("找不到MySql驱动程序包");
		}
		String connUrl="jdbc:mysql://localhost:3306/tieba?user=root&password=";
		try {
			connection= DriverManager.getConnection(connUrl);
		      Statement statement = connection.createStatement();
		      statement.executeUpdate("create table  if not exists `tieba`  (id int(10) primary key AUTO_INCREMENT, url varchar(200))engine=INNODB default charset=utf8");
		      statement.executeUpdate("ALTER TABLE tieba ADD INDEX (url)");
		      if(statement!=null) statement.close();
		} catch (SQLException e) {
			System.err.println("数据库连接失败，请检查数据库及连接字符串");
		}
		pool=Executors.newFixedThreadPool(10);
		HashMap<String, String> hsmap;
		try {
			hsmap = Tieba.getHomePageHashMap("http://tieba.baidu.com/f?ie=utf-8&kw=%E5%A7%90%E8%84%B1");
			System.out.println("主页下载完毕，准备解析");
			latch=new CountDownLatch(hsmap.size());
			for(String s:hsmap.keySet()){
				TiebaDownloader td=new TiebaDownloader();
				td.dPage=s;
				td.path=hsmap.get(s).trim().replace(".", "").replace(":", "").replace("*", "").replace("?", "").replace("\"", "").replace("<", "").replace(">", "").replace("|", "");
				pool.submit(new Thread(td));
			}
		} catch (IOException e) {
			System.err.println("获取主页数据失败，请检查网络连接");
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			System.err.println("线程池等待失败");
		}
		if(connection!=null)
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("数据库连接关闭失败");
			}
		pool.shutdown();
		System.out.println("线程池关闭");


	}

	
	@Override
	/**
	 * 启动子线程，下载贴吧的每一页
	 */
	public void run() {
		Set<String> set;
		try {
			System.out.println("准备下载子页面，标题为："+path);
			set = Tieba.getDetailsPageImageList(dPage);
			System.out.println(path+"：子页面下载完毕，正在解析");
			for(String imgLink:set){
				if(imgLink!=null){
					if(!urlIsExits(imgLink)){
						pool.submit(new Thread(new FileDownloader(imgLink,path)));
						try {
							insert(imgLink);
						} catch (SQLException e) {
							System.err.println("数据库连接失败，未能将下载地址保存在数据库中");
						}
					}
				}
				
			}
		} catch (IOException ioe) {
			System.err.println("子页面下载失败");
		}finally{
			latch.countDown();
		}
	}
	
	/**
	 * 判断一个文件下载地址在数据库中是否存在
	 * @param fileName
	 * @return
	 * @throws SQLException
	 */
	private static boolean urlIsExits(String fileName){
		Statement statement=null;
	    try {
			statement = connection.createStatement();
		} catch (SQLException e2) {
			System.err.println("数据库连接失败");
		}

		ResultSet rs =null;
		String SQL=null;
		try
	    {
			SQL="select * from tieba where url='"+fileName+"'";
			rs = statement.executeQuery(SQL);
			boolean b=false;
			if(rs!=null){
		      while(rs.next()){
		    	  b= true;
		      }
	      }
	      return b;
	    }
	    catch(SQLException e)
	    {
	    	System.err.println("出错语句为:"+SQL);
	    }finally{
	    	if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					System.err.println("关闭ResultSet时出错");
				}
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					System.err.println("关闭Statement时出错");
				}
	    }
		return false;
	}
	
	/**
	 * 把文件名插入数据库
	 * @param fileName
	 * @throws SQLException
	 */
	private static void insert(String fileName) throws SQLException
	  {
		Statement statement=null;
	    try {
			statement = connection.createStatement();
		  	String SQL="insert into tieba (id,url) values (null,'"+fileName +"')";
	    	statement.executeUpdate(SQL);
	    }
	    catch(SQLException e)
	    {
	      System.err.println("插入数据库时出错");
	    }finally{
	    	if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					System.err.println("关闭Statement时出错");
				}
	    }
	  }


}
