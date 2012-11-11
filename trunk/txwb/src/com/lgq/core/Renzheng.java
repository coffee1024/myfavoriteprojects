package com.lgq.core;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weibo4j.util.WeiboConfig;


import com.tencent.weibo.oauthv1.OAuthV1;
import com.tencent.weibo.oauthv1.OAuthV1Client;
import com.tencent.weibo.utils.QHttpClient;

/**
 *@author  Administrator
 *@date    2012-6-21 下午4:36:18
 *@version 1.0
 **/
public class Renzheng extends HttpServlet {
	private static OAuthV1 oAuth;
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(this.getClass().getResource("/").getPath());
		System.out.println("执行认证~");
		//自定制http连接管理器
		QHttpClient qHttpClient=new QHttpClient(2, 2, 1000, 1000, null, null);
		OAuthV1Client.setQHttpClient(qHttpClient);
		//读入授权码等参数
	          String auth_token= request.getParameter("oauth_token");
	          String oauth_verifier= request.getParameter("oauth_verifier");
	          String openid= request.getParameter("openid");
	          String openkey= request.getParameter("openkey");
	        System.out.println(auth_token+"--"+oauth_verifier+"--"+openid+"--"+openkey);
	       if (auth_token==null||oauth_verifier==null||openid==null||openkey==null){
	    	   System.out.println("执行if");
	    	   chushi();
	    	   // 获取request token
	    	   try {
	    		   oAuth = OAuthV1Client.requestToken(oAuth);
	    	   } catch (Exception e1) {
	    		   e1.printStackTrace();
	    	   }
	    	   
	    	   //检查是否正确取得request token
	    	   if (oAuth.getStatus() == 1) {
	    		   System.out.println("Get Request Token failed!");
	    		   return;
	    	   }
	    	   //调用外部浏览器，请求用户授权
	    	   request.getSession().setAttribute("oauth", oAuth);
	    	   response.sendRedirect(OAuthV1Client.generateAuthorizationURL(oAuth));
	    	   qHttpClient.shutdownConnection();  
	       }else {
	    	   System.out.println("执行else");
	    	 //读入授权码等参数
		       try {
		           oAuth.setOauthToken(auth_token);
		           oAuth.setOauthVerifier(oauth_verifier);
		           oAuth.setOpenid(openid);
		           oAuth.setOpenkey(openkey);
		       } catch (Exception e2) {
		           e2.printStackTrace();
		       }  
		       //检查是否正确取得授权码
		       if (oAuth.getStatus() == 2) {
		           System.out.println("Get Authorization Code failed!");
		           return;
		       }
		       
		       //换取access token
		        try {
		        System.out.println(oAuth.getOauthToken());
		            oAuth = OAuthV1Client.accessToken(oAuth);
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
		        
		       //检查是否正确取得access token
		       if (oAuth.getStatus() == 3) {
		            System.out.println("Get Access Token failed!");
		            return;
		        }
		       request.getSession().setAttribute("oauth", oAuth);
		       System.out.println("授权成功");
		       qHttpClient.shutdownConnection();
		       response.sendRedirect("success");
		       }
	}
	
    private static void chushi() {
    	oAuth=new OAuthV1();
        oAuth.setOauthConsumerKey("801163156");
        oAuth.setOauthConsumerSecret("e58b6e97fc85f62fd254efabb2274085");
        oAuth.setOauthToken("af51069722f04c15911cdf25b91bfe58");
        oAuth.setOauthTokenSecret("81a98d6117ad686eb3aad86061707e4a");
        oAuth.setOauthCallback(WeiboConfig.getValue("txcallback"));
//        oAuth.setOauthCallback("http://652874002.x6.fjjsp01.com/core/renzheng");
    }


}
