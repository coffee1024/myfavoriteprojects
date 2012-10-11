package com.travel.dao;

import java.util.List;

import com.travel.entity.Customer;

/**
 *@author 刘光强
 *@date：2012-2-21 下午12:55:54
 *@version 1.0
 **/
public interface ICustomerDao {
	/**
	 * 根据用户名查找对应的用户
	 * 
	 * @param userName
	 * @return Customer
	 */
	public Customer getCustomerByName(String userName);

	/**
	 * 根据用户Id查找对应的用户
	 * 
	 * @param uid
	 * @return Customer
	 */
	public Customer getCustomerById(Integer uid);

	/**
	 * 获取所有的用户集和
	 * 
	 * @return List<Customer>
	 */
	public List<Customer> getAllCustomers();

	/**
	 * 添加用户的方法
	 * 
	 * @param user
	 * @return 添加是否成功
	 */
	public boolean addCustomer(Customer user);
	/**
	 * 更新用户的方法
	 * @param user
	 * @return	更新是否成功
	 */
	public boolean updateCustomer(Customer user);
	/**
	 * 删除用户的方法
	 * @param user
	 * @return 删除是否成功
	 */
	public boolean deleteCustomer(Customer user);
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
	public List<Customer> getCustomersByPage(int pageNo,int count);
}
