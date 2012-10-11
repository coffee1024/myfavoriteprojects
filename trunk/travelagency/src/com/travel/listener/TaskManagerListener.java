package com.travel.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.utils.ConfigUtil;
import com.travel.utils.IndexSuggestWords;
import com.travel.utils.IndexTableByConfig;
/**
 * @author 刘光强
 * @date 2012-3-26 下午3:25:52
 * @version 1.0
 * @监听器类，监听应用启动和关闭，执行相关的定时任务
 **/
public class TaskManagerListener implements ServletContextListener {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	// 定时器
	private Timer timer;
	/**
	 * 在Web应用启动时初始化任务
	 */
	public void contextInitialized(ServletContextEvent event) {
		logger.debug("开启定时任务，时间为"
				+ DateFormatUtils.format(new Date(), "yyyy-dd-MM HH:mm:ss"));
		IndexTableByConfig indexTable=new IndexTableByConfig();
		try {
			indexTable.init();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("对表索引时出现问题"+e.toString());
		}
		// 定义定时器
				timer = new Timer("增量索引任务", true);
				// 启动清理任务,每天执行一次
				timer.schedule(new IndexTableTask(), 60000, 60000);
		}
	/**
	 * 在Web应用结束时停止任务
	 */
	public void contextDestroyed(ServletContextEvent event) {
		logger.debug("销毁定时任务，时间为"
				+ DateFormatUtils.format(new Date(), "yyyy-dd-MM HH:mm:ss"));
		timer.cancel();
	}
}