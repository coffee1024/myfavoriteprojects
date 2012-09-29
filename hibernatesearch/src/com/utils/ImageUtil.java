package com.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.process.ArrayListOutputConsumer;

/**
 *@author  liuguangqiang
 *@date    2012-9-29 上午09:05:06
 *@version 1.0
 **/
public class ImageUtil {
	public static void gmAlterImg(int size,String srcPath,String destPath,int width,int height,Boolean synFlag) throws Exception{
		try{
			//
			FileUtils.makeDirectory(destPath);
			int old_w = width;
			int old_h = height;
			int new_w = 0;
			int new_h = 0;
			if(old_w>old_h)
			{
				new_w = size;
				new_h = old_h*new_w/old_w;
			}
			else
			{
				new_h = size;
				new_w = old_w*new_h/old_h;
			}
			
			IMOperation op = new IMOperation();  
			op.addImage(srcPath);
			op.resize(new_w,new_h);
			op.addImage(destPath);
			ConvertCmd convert = new ConvertCmd(true);
			if(synFlag==null || synFlag){
				convert.setAsyncMode(true);
			}
			convert.run(op);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	public static Map<String,Integer> getPictureSize(String filePath){
		int height=0;
		int width=0;
		Map<String, Integer> map=new HashMap<String, Integer>();
		File currentFile=new File(filePath);
		if (!currentFile.exists()) {
			return map;
		}
		try {
			   Iterator readers = ImageIO.getImageReadersByFormatName("jpg");
			   ImageReader reader = (ImageReader)readers.next();
			   ImageInputStream iis = ImageIO.createImageInputStream(currentFile);
			   reader.setInput(iis, true);
			   height=reader.getHeight(0);
			   width=reader.getWidth(0);
			   // 宽
			   map.put("width", width);
	 		   // 高
			   map.put("height", height);
			  } catch (IIOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			   try{
				IMOperation op=new IMOperation();
				op.format("%w#%h");
				op.addImage(currentFile.getAbsolutePath());  
				IdentifyCmd identifyCmd = new IdentifyCmd(true);  
	            ArrayListOutputConsumer output = new ArrayListOutputConsumer();  
	            identifyCmd.setOutputConsumer(output);  
	            identifyCmd.run(op);
	            ArrayList<String> out=output.getOutput();
	            String wNh=null;
	            if(out.size()>0){
	            	wNh=out.get(0);
	            	width=Integer.parseInt(wNh.split("#")[0]);
	            	height=Integer.parseInt(wNh.split("#")[1]);
	            	// 宽
	 			   map.put("width", width);
	 	 		   // 高
	 			   map.put("height", height);
	            	}
	            }catch(Exception e1){
	            	e1.printStackTrace();
	            }
			 }catch(Exception e){
				 //TODO 
				 e.printStackTrace();
			 }
		return map;
	}
}
