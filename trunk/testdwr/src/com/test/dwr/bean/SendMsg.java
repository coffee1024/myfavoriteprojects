package com.test.dwr.bean;
/**
 *  DWR反向Ajax示例    
 *@author  liuguangqiang
 *@date    2012-11-1 下午2:55:44
 *@version 1.0
 **/
import java.text.SimpleDateFormat;
import java.util.Collection;   
import java.util.Date;
import java.util.LinkedList;   
  
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;   
import org.directwebremoting.WebContextFactory;   
import org.directwebremoting.proxy.dwr.Util;   

import com.sun.org.apache.bcel.internal.generic.NEW;


public class SendMsg{      
    public static WebContext wctx = null;     
       
    //用一个List代表数据库 来储存消息   
    public static  LinkedList list = new LinkedList();   
       
    //调用添加和显示方法      
    public void sendMsg(String msg){      
//        System.out.println(msg);
    	wctx = WebContextFactory.get();
    	String userName=wctx.getSession().getAttribute("user").toString();
    	if (userName==null||"".equals(userName)) {
			userName="匿名";
		}
        list.addFirst(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+" "+userName+"说："+msg);   
        //最多保留10条聊天记录   
        if(list.size()>50){   
            list.removeLast();   
        }              
           
        Util utilThis = new Util(wctx.getScriptSession());      
           
        //使用utilThis重置 Id 属性为 msg 的文本框的内容   
        utilThis.setValue("msg", "");    
        String currentPage = "/testdwr/sendMsg.jsp"; //要推信息的页面地址     
        //获得所有已经打开此页面的会话   
        Collection<ScriptSession> sessions = wctx.getScriptSessionsByPage(currentPage);      
//        System.out.println(sessions);
        Util utilAll = new Util(sessions);   
           
        //将消息从LinkedList中取出来，放放到一个字符串数组中   
        String[] msgs = new String[list.size()];   
        msgs = (String[]) list.toArray(msgs);   
        //先清空页面的消息内容，去除ul元素下所有元素   
        utilAll.removeAllOptions("ul");           
        //向页面添加消息内容   
        utilAll.addOptions("ul", msgs);   
    }      
} 
