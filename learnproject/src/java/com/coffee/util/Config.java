package com.coffee.util;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title 图片文件常用参数及变量
 * @Author ZhouQiang
 * @CrTime 2010-4-13 下午04:47:02
 * @Version 1.5
 */
public class Config {
	protected static Logger logger = LoggerFactory.getLogger(Config.class);

	/**
	 * 接收的图片类型的后缀
	 */
	public final static String[] Accepted_Suffix = { "jpg", "tif" };
	/**
	 * 接收的实际图片类型（MIME）
	 */
	public final static String[] Accepted_Content_Type = { "jpg", "tif" };

	/**
	 * 默认站点类型
	 */
	public final static String DEFAULT_SITE = "1";

	/**
	 * 图片大小的枚举类型
	 */
	public enum ArchivePictureSize {
		/**
		 * 小图
		 */
		SMALL, /**
				 * 中图
				 */
		MEDIUM, /**
				 * 带水印的中图
				 */
		WATERMARKEDMEDIUM, /**
							 * 大图
							 */
		BIG, /**
				 * 原图
				 */
		ORIGINAL;
	}

	/**
	 * 根据图片参数类型和图片文件名得到图片在服务器上的存放路径（没有则生成）
	 * 
	 * @param filename
	 *            规则命名的图片名称
	 * @param size
	 *            图片参数类型
	 * @return 对应图片类型的图片在服务器上的路径
	 */
	public static String getFilePath(String filename, String size, String site) {
		return getFilePathOnServer(getPictureSizeType(size), filename, site);
	}

	/**
	 * 根据图片大小类型和图片文件名得到图片在服务器上的存放路径（没有则生成）
	 * 
	 * @param aps
	 *            图片大小类型
	 * @param filename
	 *            规则命名的图片名称
	 * @param site
	 *            站点Id
	 * @return 对应图片类型的图片在服务器上的路径
	 */
	public static String getFilePathOnServer(ArchivePictureSize aps,
			String filename, String site) {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isBlank(site)) {
			site = DEFAULT_SITE;
		}
		if (StringUtils.isNotBlank(filename)) {
			switch (aps) {
			case SMALL:
				sb.append(ConfigConstant.getValue("SMALL_PIC_PATH", site));
				break;
			case MEDIUM:
				sb.append(ConfigConstant.getValue("MEDIUM_PIC_PATH", site));
				break;
			case WATERMARKEDMEDIUM:
				sb.append(ConfigConstant.getValue("WATERMARKEDMEDIUM_PIC_PATH",
						site));
				break;
			case BIG:
				sb.append(ConfigConstant.getValue("BIG_PIC_PATH", site));
				break;
			case ORIGINAL:
				sb.append(ConfigConstant.getValue("ORIGINAL_PIC_PATH", site));
				break;
			default:
				sb.append(ConfigConstant.getValue("SMALL_PIC_PATH", site));
				break;
			}
			// 无论配置中的路径后是否有路径分隔符，都在路径后再加一个分隔符
			sb.append(File.separator);
			sb.append(filename.substring(0, 4)).append(File.separator);
			sb.append(filename.substring(0, 8)).append(File.separator);
			sb.append(filename);
		} else {
			sb.append(ConfigConstant.getValue("SMALL_PIC_PATH", site));
			sb.append(File.separator);
			sb.append(ConfigConstant.getValue("DEFAULT_PICTURE", site));
		}
		FileUtils.makeDirectory(sb.toString());
		return sb.toString();
	}

	/**
	 * 根据图片文件名得到图片在服务器上的存放路径（没有则生成）
	 * 
	 * @param filename
	 *            规则命名的图片名称
	 * @param site
	 *            站点Id
	 * @return 首页图片在服务器上的路径
	 */
	public static String getCoverPicturePathOnServer(String filename, String site) {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isBlank(site)) {
			site = DEFAULT_SITE;
		}
		if (StringUtils.isNotBlank(filename)) {
			sb.append(ConfigConstant.getValue("COVER_PIC_PATH", site));
			// 无论配置中的路径后是否有路径分隔符，都在路径后再加一个分隔符
			sb.append(File.separator);
			sb.append(filename);
		} else {
			sb.append(ConfigConstant.getValue("COVER_PIC_PATH", site));
			sb.append(File.separator);
			sb.append(ConfigConstant.getValue("DEFAULT_COVER_PICTURE", site));
		}
		FileUtils.makeDirectory(sb.toString());
		return sb.toString();
	}

	/**
	 * 根据图片名得到图片在服务器上的存放路径（没有则生成）
	 * @param filename
	 * @param site
	 * @return
	 */
	public static String getWaterMarkPicturePathOnServer(String filename,String site){
		StringBuffer sb=new StringBuffer();
		if(StringUtils.isBlank(site)){
			site=DEFAULT_SITE;
		}
		if(StringUtils.isNotBlank(filename)){
			sb.append(ConfigConstant.getValue("DEFAULT_WATERMARK_PATH", site));
			sb.append(File.separatorChar);
			sb.append(filename);
		}else{
			sb.append(ConfigConstant.getValue("DEFAULT_WATERMARK_PATH", site));
			sb.append(File.separatorChar);
			sb.append(ConfigConstant.getValue("DEFAULT_WATERMARK_PIC", site));
		}
		FileUtils.makeDirectory(sb.toString());
		return sb.toString();
	}
	
	/**
	 * weixing 获取应用下临时文件目录
	 * @param filename
	 * @param site
	 * @return
	 */
	public static String getTempPathOnServer(String filename,String site){
		StringBuffer sb=new StringBuffer();
		if(StringUtils.isBlank(site)){
			site=DEFAULT_SITE;
		}
		if(StringUtils.isNotBlank(filename)){
			sb.append(ConfigConstant.getValue("TEMP_PIC_PATH", site));
			sb.append(File.separatorChar);
			sb.append(filename);
		}else{
			sb.append(ConfigConstant.getValue("TEMP_PIC_PATH", site));
			sb.append(File.separatorChar);
		}
		FileUtils.makeDirectory(sb.toString());
		return sb.toString();
	}
	/**
	 * 取到图片的参数
	 * @param filename
	 * @param site
	 * @return
	 */
	public static String getPicSizeOnServer(String filename,String site) {
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isBlank(site)){
			site = DEFAULT_SITE;
		}
		if (StringUtils.isNotBlank(filename)&&"1000".equalsIgnoreCase(filename)) {
			sb.append(ConfigConstant.getValue("PICSIZE_1000", site));	
		}
		if (StringUtils.isNotBlank(filename)&&"2000".equalsIgnoreCase(filename)) {
			sb.append(ConfigConstant.getValue("PICSIZE_2000", site));
		}
		if (StringUtils.isNotBlank(filename)&&"3000".equalsIgnoreCase(filename)) {
			sb.append(ConfigConstant.getValue("PICSIZE_3000", site));
		}
		FileUtils.makeDirectory(sb.toString());
		return sb.toString();
	}
	
	/**
	 * 根据图片参数类型获取图片大小类型
	 * 
	 * @param size
	 *            图片参数类型
	 */
	public static ArchivePictureSize getPictureSizeType(String size) {
		ArchivePictureSize aps = null;
		// size.equalsIgnoreCase("s");
		if ("O".equals(size))
			aps = ArchivePictureSize.ORIGINAL;
		else if ("B".equals(size))
			aps = ArchivePictureSize.BIG;
		else if ("M".equals(size))
			aps = ArchivePictureSize.MEDIUM;
		else if ("W".equals(size))
			aps = ArchivePictureSize.WATERMARKEDMEDIUM;
		else
			aps = ArchivePictureSize.SMALL;
		return aps;
	}
}
