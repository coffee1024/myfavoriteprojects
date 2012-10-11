package com.travel.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Field;
import org.apache.lucene.util.Version;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.entity.LuceneField;
import com.travel.entity.LuceneTable;

/**
 *@author  刘光强
 *@date    2012-4-25 下午2:56:35
 *@version 1.0
 **/
public class LuceneConfig {
	
	public static final Version version=Version.LUCENE_CURRENT;
	private static final String CONFIG_FILE="/com/travel/sources/mylucene.xml";
	private static Logger logger=LoggerFactory.getLogger(LuceneConfig.class);
	private static List<LuceneTable> tables=new ArrayList<LuceneTable>();
	
	/**
	 * 隐藏构造方法
	 */
	private LuceneConfig(){
	}
	static{
		config(CONFIG_FILE);
	}
	
	
	public static List<LuceneTable> getTables() {
		return tables;
	}



	public static void setTables(List<LuceneTable> tables) {
		LuceneConfig.tables = tables;
	}



	public static void config(String configFile) {
		InputStream in=LuceneConfig.class.getResourceAsStream(configFile);
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(in);
		} catch (DocumentException e) {
			logger.error("初始化自定义lucene配置信息时出错"+e.getMessage());
			return;
		}
		//获取根元素tables
		long a=System.currentTimeMillis();
		Element element = document.getRootElement();
		List<Element> etables = element.elements();
		for (int z = 0; z < etables.size(); z++) {
			//獲取table元素
			Element etable=etables.get(z);
			String tablename=etable.attributeValue("name");
			List<Element> list = etable.elements();
			LuceneTable table=new LuceneTable();
			List<LuceneField> fields=new ArrayList<LuceneField>();
			for (int i = 0; i < list.size(); i++) {
					//获取field元素
					Element efield=list.get(i);
					String fieldname =efield.attributeValue("column");
					LuceneField  field=new LuceneField();
					field.setFieldName(fieldname);
					List<Element> list2=efield.elements();
					Field.Store store=Field.Store.NO;
					Field.Index index=Field.Index.NOT_ANALYZED;
					Field.TermVector termVector=Field.TermVector.WITH_POSITIONS_OFFSETS;
					String tmp;
					for (int j = 0; j < list2.size(); j++) {
						//获取key元素
							Element ekey=list2.get(j);
							if ("store".equals(ekey.attributeValue("name"))) {
								tmp=ekey.getStringValue();
								if (tmp.equals("YES")) {
									store=Field.Store.YES;
								}
							}
							if ("index".equals(ekey.attributeValue("name"))) {
								tmp=ekey.getStringValue();
								if (tmp.equals("ANALYZED")) {
									index=Field.Index.ANALYZED;
								}
								if (tmp.equals("ANALYZED_NO_NORMS")) {
									index=Field.Index.ANALYZED_NO_NORMS;
								}
								if (tmp.equals("NO")) {
									index=Field.Index.NO;
								}
								if (tmp.equals("NOT_ANALYZED_NO_NORMS")) {
									index=Field.Index.NOT_ANALYZED_NO_NORMS;
								}
							}
							if ("termVector".equals(ekey.attributeValue("name"))) {
								tmp=ekey.getStringValue();
								if (tmp.equals("NO")) {
									termVector=Field.TermVector.NO;
								}
								if (tmp.equals("WITH_OFFSETS")) {
									termVector=Field.TermVector.WITH_OFFSETS;
								}
								if (tmp.equals("YES")) {
									termVector=Field.TermVector.YES;
								}
								if (tmp.equals("WITH_POSITIONS")) {
									termVector=Field.TermVector.WITH_POSITIONS;
								}
							}
						}
						field.setIndex(index);
						field.setStore(store);
						field.setTermVector(termVector);
						fields.add(field);
					}
						table.setTableName(tablename);
						table.setFields(fields);
					tables.add(table);
			}
		long b=System.currentTimeMillis();
		logger.debug("初始化Lucene配置信息完成。。。耗时"+(b-a)+"毫秒");
	}
}
