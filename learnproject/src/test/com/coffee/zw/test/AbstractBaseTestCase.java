package com.coffee.zw.test;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;


/**
 *@author  liuguangqiang
 *@date    2012-10-23 下午05:38:04
 *@version 1.0
 **/
public class AbstractBaseTestCase {
	private SessionFactory factory;
	private Session session;
	protected ClassPathXmlApplicationContext  fscontext;
	
	@Before
	public void openSession(){
		fscontext=new ClassPathXmlApplicationContext("/applicationContext.xml");
		factory=(SessionFactory)fscontext.getBean("sessionFactory");
		session=SessionFactoryUtils.getSession(factory, true);
		session.setFlushMode(FlushMode.COMMIT);
		TransactionSynchronizationManager.bindResource(factory, new SessionHolder(session));
		
	}
	@After
	public void closeSession(){
		TransactionSynchronizationManager.unbindResource(factory);
		SessionFactoryUtils.releaseSession(session, factory);
	}
}
