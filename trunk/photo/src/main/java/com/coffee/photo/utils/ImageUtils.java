package com.coffee.photo.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.im4java.core.CompositeCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.process.ArrayListOutputConsumer;

public class ImageUtils {
	/**
	 * 输出水印图
	 * 
	 * @param waterPic 生成的带水印的图
	 * @param srcPic 原图
	 * @param waterMarkerPic 水印图
	 * @param place  水印位置:center 居中；southwest 左下；southeast 右下
	 * @param synFlag  是否异步
	 * @throws IM4JavaException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void waterMarkPic(String waterPic,String srcPic,String waterMarkerPic,String place,Boolean synFlag) throws IOException, InterruptedException, IM4JavaException{
		FileUtils.makeDirectory(waterPic);
			IMOperation op = new IMOperation();
			op.gravity(place);
			op.addImage(waterMarkerPic);
			op.addImage(srcPic);
			op.addImage(waterPic);
			CompositeCmd convert = new CompositeCmd(true);
			if(synFlag==null || synFlag){
				convert.setAsyncMode(true);
			}
			convert.run(op);
	}
	
	/**
	 * 读取图片的宽高比，返回map
	 * @param currentFile
	 * @param trsLoggerManager
	 * @return
	 */
	public static Map<String,Integer> getPictureSize(String filePath){
		int height=0;
		int width=0;
		Map<String, Integer> map=new HashMap<String, Integer>();
		File currentFile=new File(filePath);
		if (!currentFile.exists()) {
			return map;
		}
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
		return map;
	}
}
