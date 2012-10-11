package com.travel.dao;

import java.util.List;

import com.travel.entity.AdminGroup;
import com.travel.entity.AdminUser;

/**
 *@author 刘光强
 *@date：2012-2-21 下午12:55:54
 *@version 1.0
 **/
public interface IGroupDao {
	/**
	 * 根据名查找对应的用户组
	 * 
	 * @param userName
	 * @return AdminUser
	 */
	public AdminGroup getGroupByName(String groupName);

	/**
	 * 根据Id查找对应的用户组
	 * 
	 * @param uid
	 * @return AdminUser
	 */
	public AdminGroup getGroupById(Integer gid);

	/**
	 * 获取所有的用户组集和
	 * 
	 * @return List<AdminUser>
	 */
	public List<AdminGroup> getAllGroups();

	/**
	 * 添加用户组的方法
	 * 
	 * @param user
	 * @return 添加是否成功
	 */
	public boolean addGroup(AdminGroup group);
	/**
	 * 更新用户的方法
	 * @param user
	 * @return	更新是否成功
	 */
	public boolean updateGroup(AdminGroup group);
	/**
	 * 删除用户的方法
	 * @param user
	 * @return 删除是否成功
	 */
	public boolean deleteGroup(AdminGroup group);
	
}
