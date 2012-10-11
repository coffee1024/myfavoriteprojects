package com.travel.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 *@author  刘光强
 *@date    2012-4-25 上午9:24:37
 *@version 1.0
 **/
public class PinYinUtils {
	
	private static HanyuPinyinOutputFormat format=new HanyuPinyinOutputFormat();
	static{
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
	}
	public static String getFirstPinYinString(String string) throws Exception{
		char[] chars=string.toCharArray();
		StringBuffer sb=new StringBuffer("");
		for (int i = 0; i < chars.length; i++) {
			String[] strings=PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
			if (strings!=null) {
					sb.append(strings[0]);
			}
		}
		return sb.toString();
	}
}
