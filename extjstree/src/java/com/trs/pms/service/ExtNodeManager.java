package com.trs.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trs.pms.core.orm.hibernate.EntityManager;
import com.trs.pms.dao.ExtNodeDao;
import com.trs.pms.domain.ExtNode;
/**
 * @Title 收藏夹管理类. 实现收藏夹的所有业务管理函数. 演示派生DAO层子类的模式,由注入的UserDao封装数据库访问.
 *        通过泛型声明继承EntityManager,默认拥有CRUD管理方法. 使用Spring annotation定义事务管理.
 * 
 * @CreateTime 2012-11-8
 * @Version 3.0
 */
@Service
@Transactional
public class ExtNodeManager extends EntityManager<ExtNode, Long> {

	@Autowired
	private ExtNodeDao extNodeDao;
	
	@Override
	protected ExtNodeDao getEntityDao() {
		return extNodeDao;
	}
	
}