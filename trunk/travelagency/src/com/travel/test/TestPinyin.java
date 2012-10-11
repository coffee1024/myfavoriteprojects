package com.travel.test;

import com.travel.utils.LoadSuggestWords;
import com.travel.utils.PinYinUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 *@author  刘光强
 *@date    2012-4-25 上午8:53:21
 *@version 1.0
 **/
public class TestPinyin {
	public static void main(String[] args) throws Exception {
//		HanyuPinyinOutputFormat format=new HanyuPinyinOutputFormat();
//		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		format.setVCharType(HanyuPinyinVCharType.WITH_V);
//		String string="你是水";
//		char[] chars=string.toCharArray();
//		for (int i = 0; i < chars.length; i++) {
//			String[] strings=PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
//			if (strings!=null) {
//				System.out.println(strings[0]);
//			}
//		}
//		String string=PinYinUtils.getFirstPinYinString("调味品");
//		System.out.println(string);
		LoadSuggestWords loadSuggestWords=new LoadSuggestWords();
		loadSuggestWords.init();
	}
}
