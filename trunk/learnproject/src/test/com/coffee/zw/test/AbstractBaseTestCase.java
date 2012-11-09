package com.coffee.zw.test;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;


/**
 * 测试基类，加载spring配置文件，获取session
 *@Author        liuguangqiang
 *@CreateTime    2012-10-23
 *@Version       3.0
 **/
public class AbstractBaseTestCase{
	private SessionFactory factory;
	private Session session;
	protected ClassPathXmlApplicationContext  fscontext;
	
	@Before
	public void openSession(){
		fscontext=new ClassPathXmlApplicationContext("/applicationContext.xml");
		factory=(SessionFactory)fscontext.getBean("sessionFactory");
		session=SessionFactoryUtils.openSession(factory);
		session.setFlushMode(FlushMode.COMMIT);
		TransactionSynchronizationManager.bindResource(factory, new SessionHolder(session));
		
	}
	@After
	public void closeSession(){
		TransactionSynchronizationManager.unbindResource(factory);
		SessionFactoryUtils.closeSession(session);
	}
	
}
