package com.test;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *@author  liuguangqiang
 *@date    2012-9-24 下午05:38:34
 *@version 1.0
 **/
@Repository
public class Domain{  
	@Anno(isAop = "Y")
    public void save(String name) {  
        System.out.println("我是save方法");  
    }  
  
    public void update(String name, Integer id) {  
          
        System.out.println("我是update()方法");  
    }  
  
    public String getPersonName(Integer id) {  
          
        System.out.println("我是getPersonName()方法");  
        return "xxx";  
    }  
  
}  
