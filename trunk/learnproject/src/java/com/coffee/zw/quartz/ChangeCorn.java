package com.coffee.zw.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.coffee.zw.util.ConfigConstant;

public class ChangeCorn{
	
	private static String INSTANCE_NAME = "DEFAULT";
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	
    /**
     * 更改清理上传的错误图片任务的时间
     * @author liuguangqiang
     * @date 2012-8-17
     */
    public void changeClearUploadTime() throws Exception{
    	doChange("CLEAR_UPLOAD_TIME","clearuploadjobtask","clearuploadcron");
    }
    
    private static Scheduler scheduler;
    
    public static void setScheduler(){
    	ApplicationContext context = new ClassPathXmlApplicationContext(
	     "spring/apctx-quartz.xml");
   		scheduler = (Scheduler) context.getBean("dologger");
    }

    private void doChange(String valueName,String task,String triggerName) throws Exception{
     	String newClearTime=ConfigConstant.getValue(valueName, 1L);
        JobDetail myJob = scheduler.getJobDetail(task, INSTANCE_NAME);
//	        String[] groups=sch.getJobGroupNames();
//	        for(String group:groups){
//	        	logger.debug("定时任务组："+group);
//	        	String[] jobNames=sch.getJobNames(group);
//	        	for(String jobName:jobNames){
//	        		logger.debug("定时任务："+jobName);
//	        		Trigger[] triggers=sch.getTriggersOfJob(jobName, group);
//	        		for(Trigger trigger:triggers){
//	        			logger.debug("定时任务时间规则："+trigger);
//	        		}
//	        	}
//	        }
        CronTrigger newTrigger = new CronTrigger(triggerName, INSTANCE_NAME,task,INSTANCE_NAME,
        		newClearTime);
        JobDetail newJob = (JobDetail) myJob.clone();
        scheduler.rescheduleJob(triggerName, INSTANCE_NAME, newTrigger);
//        sch.pauseTrigger(triggerName,INSTANCE_NAME);//停止触发器
//        sch.unscheduleJob(triggerName,INSTANCE_NAME);//移除触发器
//        sch.deleteJob(task, INSTANCE_NAME);//删除任务
//        sch.scheduleJob(newJob, newTrigger);
        if(scheduler.isShutdown()){
        	scheduler.start();
        }
    }
}
