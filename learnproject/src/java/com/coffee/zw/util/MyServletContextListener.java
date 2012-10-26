package com.coffee.zw.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffee.zw.quartz.ChangeCorn;

public class MyServletContextListener implements ServletContextListener{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 应用销毁时干什么事写这
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 应用启动时，初始化配置表放入内存，修改定时任务时间设置
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		try{
			ConfigConstant.reload();
//			ChangeCorn.setScheduler();
//			Long siteId=Long.parseLong(ConfigConstant.getDefaultSite());
//			ChangeCorn changeCron=new ChangeCorn();
//			String newLoggerTime=ConfigConstant.getValue("LOG_BACKUP_TIME", siteId);
//			if(newLoggerTime!=null){
//				//改变日志定时任务时间设置
//				changeCron.changeTime();
//			}
//			String newPaperTime=ConfigConstant.getValue("GET_PAPER_TIME", siteId);
//			if(newPaperTime!=null){
//				changeCron.changePaperTime();
//			}
//			String newClearFbkTime=ConfigConstant.getValue("CLEAR_FBK_TIME", siteId);
//			if(newClearFbkTime!=null){
//				changeCron.changeClearFbkTime();
//			}
//			String newClearCpkTime=ConfigConstant.getValue("CLEAR_CPK_TIME", siteId);
//			if(newClearCpkTime!=null){
//				changeCron.changeClearCpkTime();
//			}
//			String newClearPicOPeTime=ConfigConstant.getValue("CLEAR_PICOPE_TIME", siteId);
//			if(newClearPicOPeTime!=null){
//				changeCron.changeClearPicOpeTime();
//			}
		}catch(Exception e){
			logger.error("初始化配置失败", e.getMessage());
		}
	}

}
