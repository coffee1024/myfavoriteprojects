package com.travel.dao;

import java.util.List;

import com.travel.entity.AdminGroup;
import com.travel.entity.AdminUser;
import com.travel.entity.CustomerGroup;

/**
 *@author 刘光强
 *@date：2012-2-21 下午12:55:54
 *@version 1.0
 **/
public interface ICustomerGroupDao {
	/**
	 * 根据名查找对应的用户组
	 * 
	 * @param userName
	 * @return AdminUser
	 */
	public CustomerGroup getGroupByName(String groupName);

	/**
	 * 根据Id查找对应的用户组
	 * 
	 * @param uid
	 * @return AdminUser
	 */
	public CustomerGroup getGroupById(Integer gid);

	/**
	 * 获取所有的用户组集和
	 * 
	 * @return List<AdminUser>
	 */
	public List<CustomerGroup> getAllGroups();

	/**
	 * 添加用户组的方法
	 * 
	 * @param user
	 * @return 添加是否成功
	 */
	public boolean addGroup(CustomerGroup group);
	/**
	 * 更新用户的方法
	 * @param user
	 * @return	更新是否成功
	 */
	public boolean updateGroup(CustomerGroup group);
	/**
	 * 删除用户的方法
	 * @param user
	 * @return 删除是否成功
	 */
	public boolean deleteGroup(CustomerGroup group);
	
}
