package test;

import com.lgq.dao.WeiboDao;

/**
 *@author  liuguangqiang
 *@date    2012-11-14 上午11:56:06
 *@version 1.0
 **/
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeiboDao weiboDao=new WeiboDao();
		weiboDao.addWeibo("测试");
	}

}
