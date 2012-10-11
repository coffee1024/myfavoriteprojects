package com.travel.action.lucene;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GetKeyWords {
	private static Logger logger=LoggerFactory.getLogger(GetKeyWords.class);
	
	public static List<String> cutKeyWords(String string,int maxnum) {
		List<String> result=new ArrayList<String>();
		Analyzer analyzer=new PaodingAnalyzer();
		KeyWords  keyWords=new KeyWords(analyzer);
		List<String> list=keyWords.getKeyWords(string);
		Map<String, Integer> map=new LinkedHashMap<String, Integer>();
		for (int i = 0; i <list.size(); i++) {
			String tmp=list.get(i);
			if (map.containsKey(tmp)) {
				Integer integer=map.get(tmp);
				map.put(tmp,integer+1);
			}else {
				map.put(tmp,1);
			}
		}
		logger.debug("获取关键词抽取结果为"+map.toString());
		Set set = map.entrySet();  
	    Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]); 
	    Arrays.sort(entries, new Comparator() {  
	           public int compare(Object arg0, Object arg1) {  
	               Integer key1 = Integer.valueOf(((Map.Entry) arg0).getValue().toString());  
	               Integer key2 = Integer.valueOf(((Map.Entry) arg1).getValue().toString());  
	               return key2.compareTo(key1);  
	           }  
	       });  
	    int resultnum=entries.length;
			if (maxnum<=0) {
				maxnum=5;
				}
		    if (maxnum>=resultnum) {
				maxnum=resultnum;
		    	}
	    for (int i = 0; i <maxnum; i++) {
	    	result.add(entries[i].getKey().toString());
		}
	    return result;
	}
	public static String cutKeyWordsString(String string,int maxnum){
		List<String> list=cutKeyWords(string, maxnum);
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)+";");
		}
		return sb.substring(0,sb.length()-1);
	}
}