package com.travel.dao;


/**
 *@author  刘光强
 *@date    2012-3-6 下午03:07:54
 *@version 1.0
 **/
public class DaoFactory {
	private static  IUserDao userDao=new UserDao();
	private static  IGroupDao groupDao=new GroupDao();
	private static  ICustomerDao customerDao=new CustomerDao();
	private static  ICustomerGroupDao customerGroupDao=new CustomerGroupDao();
	private static IRouteDao routeDao=new RouteDao();
	private static IAgencyDao agencyDao=new AgencyDao();
	private static ISuggestWordDao suggestWordDao=new SuggestWordDao();
	/**
	 * 获取UserDao实现类
	 * @return IUserDao
	 */
	public static IUserDao getUserDao(){
		if (userDao==null) {
			userDao=new UserDao();
		}
		return userDao;
	}
	public static IRouteDao getRouteDao(){
		if (routeDao==null) {
			routeDao=new RouteDao();
		}
		return routeDao;
	}
	public static IAgencyDao getAgencyDao(){
		if (agencyDao==null) {
			agencyDao=new AgencyDao();
		}
		return agencyDao;
	}
	/**
	 * 获取GroupDao实现类
	 * @return IUserDao
	 */
	public static IGroupDao getGroupDao(){
		if (groupDao==null) {
			groupDao=new GroupDao();
		}
		return groupDao;
	}
	
	public static ICustomerDao getCustomerDao(){
		if (customerDao==null) {
			customerDao=new CustomerDao();
		}
		return customerDao;
	}
	public static ICustomerGroupDao getCustomerGroupDao(){
		if (customerGroupDao==null) {
			customerGroupDao=new CustomerGroupDao();
		}
		return customerGroupDao;
	}
	public static ISuggestWordDao getSuggestWordDao(){
		if (suggestWordDao==null) {
			suggestWordDao=new SuggestWordDao();
		}
		return suggestWordDao;
	}
}

