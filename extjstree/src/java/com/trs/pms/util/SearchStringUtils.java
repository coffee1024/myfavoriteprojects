package com.trs.pms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 对TRS检索中特殊字符转义
 *
 */
public class SearchStringUtils {
	/**
	 * 过滤非法字符方法
	 * 
	 * @param str
	 *            过滤的方法
	 */
	public static String stringFilter(String str) throws PatternSyntaxException {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	/**
	 * 对特殊字符转义
	 * 
	 * TRS 检索表达式中有四类语法符号： 1. 第一类符号：‘(’、‘)’、‘[’、‘]’、‘，’、‘/’、‘@’、‘=’、‘>’、‘<’、‘!’、
	 * ‘&’、‘*’、‘^’、‘-’、‘+’。 2. 第二类符号：‘ADJ’、‘EQU’、‘PRE’、‘AND’、‘XOR’、‘NOT’、‘OR’、
	 * ‘TO’、空格、函数名。 3. 第三类符号：模糊匹配符‘%’、‘?’。 4. 第四类符号：单引号‘’’和转义符‘\’。
	 * 检索表达式的键值必须对这些符号进行特殊处理： 􀁺 如果键值中含有第一类语法符号，则必须将该键值用单引号引起来，或者用转义
	 * 符将这些语法符号进行转义。如：日期键值‘,,8’、\,\,8。 􀁺 如果第二类语法符号是一个键值，则必须将该键值用单引号引起来，或者用转义
	 * 符将这些语法符号进行转义。如：‘AND’、\AND。 􀁺 如果第三类或第四类语法符号是键值的一部分，则必须使用转义符将这些语法符
	 * 号进行转义。如：10\%、\\。
	 * 
	 */
	public static String toTrsString(String string) {
		try {
			if (string == null)
				return string;
			String temp = string.trim();
			//TRS检索表达式中的一些特殊字符
			char[] s = { '(', ')', '[', ']', '，', '/', '@', '=', '>', '<', '!',
					'*', '^', '-', '+', '%', '?', '\\', '\'' }; // , '’'
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < temp.length(); i++) {
				for (int j = 0; j < s.length; j++) {
					if ((temp.charAt(i)) == s[j]) {
						sb.append("\\");
						break;
					}
				}
				sb.append(temp.charAt(i));
			}
			temp = sb.toString();
			return temp;
		} catch (Exception e) {
			return string;
		}
	}
}