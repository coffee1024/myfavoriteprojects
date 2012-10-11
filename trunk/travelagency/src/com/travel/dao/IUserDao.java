package com.travel.dao;

import java.util.List;

import com.travel.entity.AdminUser;

/**
 *@author 刘光强
 *@date：2012-2-21 下午12:55:54
 *@version 1.0
 **/
public interface IUserDao {
	/**
	 * 根据用户名查找对应的用户
	 * 
	 * @param userName
	 * @return AdminUser
	 */
	public AdminUser getUserByName(String userName);

	/**
	 * 根据用户Id查找对应的用户
	 * 
	 * @param uid
	 * @return AdminUser
	 */
	public AdminUser getUserById(Integer uid);

	/**
	 * 获取所有的用户集和
	 * 
	 * @return List<AdminUser>
	 */
	public List<AdminUser> getAllUsers();

	/**
	 * 添加用户的方法
	 * 
	 * @param user
	 * @return 添加是否成功
	 */
	public boolean addUser(AdminUser user);
	/**
	 * 更新用户的方法
	 * @param user
	 * @return	更新是否成功
	 */
	public boolean updateUser(AdminUser user);
	/**
	 * 删除用户的方法
	 * @param user
	 * @return 删除是否成功
	 */
	public boolean deleteUser(AdminUser user);
	/**
	 * 判断用户是否存在的应用
	 * @param userName
	 * @return
	 */
	public boolean userIsExist(String userName);
	
	/**
	 * 获取用户数量
	 */
	public int getCount();
	/**
	 * 分页获取用户数据
	 * @param pageNo
	 * @param count
	 * @return
	 */
	public List<AdminUser> getUsersByPage(int pageNo,int count);
}
