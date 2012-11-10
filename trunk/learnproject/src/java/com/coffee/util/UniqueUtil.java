package com.coffee.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UniqueUtil {

	private static int i = 1;

	public static String format(int str) {
		DecimalFormat df = new DecimalFormat("000");
		return df.format(str);
	}

	public synchronized static int getI() {
		int a = i;
		i = i + 1;
		return a;
	}

	public static String dealResult(String name) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return name + "_" + format(getI()).toString() + "_"
				+ sdf.format(new Date()).toString();
	}

}
