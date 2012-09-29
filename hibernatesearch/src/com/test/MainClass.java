package com.test;

import java.io.File;
import java.util.Map;

import com.utils.ImageUtil;

/**
 *@author  liuguangqiang
 *@date    2012-9-29 上午09:06:26
 *@version 1.0
 **/
public class MainClass {
	public static void main(String[] args) throws Exception {
		File file=new File("E:/图片/20120928");
		String path="E:/图片/20120928_1000/";
		File[] files=file.listFiles();
		for (int i = 0; i < files.length; i++) {
			Map<String, Integer> map=ImageUtil.getPictureSize(files[i].getAbsolutePath());
			ImageUtil.gmAlterImg(1000, files[i].getAbsolutePath(), path+files[i].getName(), map.get("width"), map.get("height"), false);
		}
	}
}
