package com.travel.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.dao.JdbcDaoSupport;
import com.travel.entity.SuggestWord;
import com.travel.utils.PinYinUtils;


/**
 *@author  刘光强
 *@date    2012-4-24 下午5:24:38
 *@version 1.0
 **/
public class SuggestWordDao extends JdbcDaoSupport implements ISuggestWordDao {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
/**
 * 获取检索输入时的提示词
 */
public  List<String> getSuggest(String searchword,int num) throws Exception{
	List<String> suggestwords =new ArrayList<String>();
	if (!StringUtils.isEmpty(searchword)) {
		String sql="";
		if (checkChar(searchword.charAt(0))) {
			sql="select words from searchwords where words like '"+searchword+"%' order by searchnum desc";
		}else {
			sql="select words from searchwords where pinyin like '"+searchword+"%' order by searchnum desc";
		}
	ResultSet rs=super.query(sql);
	int i=0;
	try {
		while (rs.next()&&i<num) {
			i++;
			suggestwords.add(rs.getString("words"));
			}
		} catch (Exception e) {
		logger.error("获取检索提示词失败"+e.getMessage());
		}
	}
	return suggestwords;
}
/**
 * 获取检索完成时的提示词
 */
public  List<String> getResultSuggest(String searchword,int num) throws Exception{
	List<String> suggestwords =new ArrayList<String>();
	if (!StringUtils.isEmpty(searchword)) {
		String sql="";
		if (checkChar(searchword.charAt(0))) {
			String tmp=searchword;
			searchword=PinYinUtils.getFirstPinYinString(searchword);
			sql="select words from suggestwords where pinyin = '"+searchword+"' and words <> '"+tmp+"' order by searchnum desc";
		}else {
			sql="select words from suggestwords where pinyin = '"+searchword+"' order by searchnum desc";
		}
		ResultSet rs=super.query(sql);
		int i=0;
		try {
			while (rs.next()&&i<num) {
				i++;
				suggestwords.add(rs.getString("words"));
				}
			} catch (Exception e) {
			logger.error("获取检索结果提示词失败"+e.getMessage());
			}
		}
		return suggestwords;
}
/**
 * 插入检索词
 * 
 */
public  int insertResultSuggest(String word) throws Exception{
	if (!StringUtils.isEmpty(word)) {
		String sql="";
		Object[] params=null;
		if (checkChar(word.charAt(0))) {
			String pinyin=PinYinUtils.getFirstPinYinString(word);
			SuggestWord sw=GetSuggestWordByWord(word);
			if (sw==null) {
				sql="insert into searchwords values(null,1,?,?)";
				params=new Object[]{pinyin,word};
			}else {
				sql="update searchwords set searchnum= ? where words= ?";
				params=new Object[]{sw.getSearchnum()+1,sw.getWords()};
			}
			return super.update(sql, params);
		}
		return 0;
	}else {
		return 0;		
	}
}
/**
 * 根据中文词返回整条记录
 */
public  SuggestWord GetSuggestWordByWord(String word) throws Exception{
	if (!StringUtils.isEmpty(word)) {
		String sql="select * from searchwords where words = ? order by searchnum desc";
		ResultSet rs=super.query(sql, new String[]{word});
		if (rs==null) {
			return null;
		}
		SuggestWord sw=null;
		while (rs.next()) {
			sw=new SuggestWord();
			sw.setId(rs.getInt("id"));
			sw.setPinyin(rs.getString("pinyin"));
			sw.setWords(rs.getString("words"));
			sw.setSearchnum(rs.getInt("searchnum"));
			}
		return sw;
		}else {
			return null;
		}
	
}
private  boolean checkChar(char oneChar){
		if((oneChar >= '\u4e00' && oneChar <= '\u9fa5')
		||(oneChar >= '\uf900' && oneChar <='\ufa2d'))
		return true;
		return false;
	}
}
