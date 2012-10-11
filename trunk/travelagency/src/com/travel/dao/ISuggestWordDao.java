package com.travel.dao;

import java.util.List;

import com.travel.entity.SuggestWord;

/**
 *@author  刘光强
 *@date    2012-4-25 下午12:00:22
 *@version 1.0
 **/
public interface ISuggestWordDao {

	/**
	 * 获取检索智能提示信息的方法
	 * @param searchword
	 * @param num
	 * @return
	 */
	public abstract List<String> getSuggest(String searchword, int num)
			throws Exception;

	/**
	 * 获取检索后的建议词的方法
	 * @param searchword
	 * @param num
	 * @return
	 */
	public abstract List<String> getResultSuggest(String searchword, int num)
			throws Exception;

	public abstract int insertResultSuggest(String word) throws Exception;

	public abstract SuggestWord GetSuggestWordByWord(String word)
			throws Exception;

}