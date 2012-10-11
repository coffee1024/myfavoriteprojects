package com.travel.listener;

import java.util.Date;
import java.util.TimerTask;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.utils.IndexSuggestWords;
import com.travel.utils.IndexTableByConfig;

/**
 * @author 刘光强
 * @date 2012-3-26 下午3:29:21
 * @version 1.0
 * @清理头像缓存目录的定时任务
 **/
public class IndexTableTask extends TimerTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static boolean isRunning = false;

	public void run() {
		if (!isRunning) {
			isRunning = true;
			logger.debug("对表索引开始执行...时间为"+DateFormatUtils.format(new Date(), "yyyy-dd-MM HH:mm:ss")); // 任务完成
			IndexTableByConfig indexTable=new IndexTableByConfig();
			try {
				indexTable.init();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("对表索引时出现问题"+e.toString());
			}
			isRunning = false;
		} else {
			logger.debug("上一次任务执行还未结束..."); // 上一次任务执行还未结束
		}
	}
}
