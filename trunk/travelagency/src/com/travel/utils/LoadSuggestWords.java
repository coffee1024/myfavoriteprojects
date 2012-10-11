package com.travel.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.dao.JdbcDaoSupport;


/**
 * @author 刘光强
 * @date 2012-4-4 上午11:43:08
 * @version 1.0
 **/
public class LoadSuggestWords extends JdbcDaoSupport{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	public  void init() throws Exception{
		long a=System.currentTimeMillis();
		BufferedReader b=new BufferedReader((new InputStreamReader(new FileInputStream("E:/各种词典/pinyin_new_new.txt"),"UTF-8")));
		String[] strs;
		String string;
		int i=0;
		conn=super.getConnection();
		Statement s=conn.createStatement();
		Map<String, String> map=new HashMap<String, String>();
		while ((string=b.readLine())!=null) {
			strs=string.split(" ");
			map.put(strs[1].trim(), strs[0].trim());
		}
		
		Set<String> set=map.keySet();
		Iterator<String> in=set.iterator();
		while (in.hasNext()) {
			i++;
			String key=in.next();
			String value=map.get(key);
			String sql="insert into suggestwords values (null,0,'"+value+"','"+key+"')";
			System.out.println(i);
			s.addBatch(sql);
			if (i%1000==0) {
				s.executeBatch();
				s.clearBatch();
			}
		}
		s.executeBatch();
		super.close(null, ps, conn);
			long c=System.currentTimeMillis();
			logger.debug("向数据库加载检索提示词索引结束，耗时："+(c-a)+"毫秒");
		}
}
