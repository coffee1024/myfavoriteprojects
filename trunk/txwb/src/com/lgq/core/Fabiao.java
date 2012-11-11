package com.lgq.core;

import java.io.File;
import java.util.Random;
import java.util.TimerTask;

import weibo4j.util.WeiboConfig;

import net.sf.json.JSONObject;

import com.lgq.dao.PicDao;
import com.lgq.dao.WeiboDao;
import com.tencent.weibo.api.FriendsAPI;
import com.tencent.weibo.api.TAPI;
import com.tencent.weibo.oauthv1.OAuthV1;

/**
 *@author  Administrator
 *@date    2012-6-14 下午4:24:25
 *@version 1.0
 **/
public class Fabiao extends TimerTask {
	private  OAuthV1 oAuth;
	private int i=1;
	public Fabiao(OAuthV1 oAuth){
		System.out.println("新建定时任务");
		this.oAuth=oAuth;
	}
	public void run() {
/*
 * -----------------------------------------简化测试参数 begin-----------------------------------------------
 *     下列每个参数的具体含义和取值，请参看其所在函数的doc文档
 */
		String response;
	    String format="json";
	    String clientip="127.0.0.1";
	    String jing ="";
	    String wei ="";
	    String syncflag="";
	    String pageflag="0";
	    String pagetime="0";
	    String reqnum="5";
	    String lastid="'0";
	    String contenttype="0";
	    String content="1";// 注意：因为后台会对微博内容进行判重，所以在重复测试时加上变换部分++++++++
	    String twitterid="0";
	    String fopenids="";
	    String fopenid="";
	    String reid=null;
	    String ids=null;
	    String id=null;
	    String names="api_weibo,t-qq-com,vvtest1";
	    String name="t-qq-com";
	    String flag="2";
        String keyword="微博"; 
        String pagesize="5";
        String page="0";
        String searchtype="0";
        String msgtype="0";
        String sorttype ="0";
        String type="0";
        String op="0";
        String starttime="";
        String endtime="";
        String province="";
        String city="";
        String longitue="";
        String latitude="";
        String radius="";
        String startindex="0";
        String mode="0";
        String install="0";
        int total=new WeiboDao().getCount();
        String context="";
        do {
        	Random random=new Random();
        	int tmp=random.nextInt(total);
        	if (tmp!=i) {
        		i=tmp;
        	}else {
        		i=random.nextInt(total);
        	}
        	context=new WeiboDao().getString(i);
        	System.out.println("context length="+context.length());
		} while (context.length()>140);
        if ("yes".equals(WeiboConfig.getValue("tuijian"))&&context.length()<=122) {
			context=context+"喜欢请收听@lgq652874007";
		}
        String picname="";
        try {
        	int pictotal=new PicDao().getCount();
        	Random random=new Random();
        	int tmp=random.nextInt(pictotal);
        	picname=new PicDao().getString(tmp);
        	picname=WeiboConfig.getValue("picrooturl")+"/pic/"+picname;
		} catch (Exception e) {
			// TODO: handle exception
		}
/*
 * -----------------------------------------简化测试参数 end-----------------------------------------------
 */
	    
	    
/*
*---------------------------------------- 微博相关测试例 begin---------------------------------------------
*   注意：
*   微博服务器对发微博的频率有限制，如果不加 sleep() 直接执行下列多条发微博操作， 
*   可能会出现 ret=4 errcode=10 的错误码，意思是：发表太快，被频率限制 
*/
		    
            //取得返回结果
            try {
            	TAPI tAPI=new TAPI(oAuth.getOauthVersion());//根据oAuth配置对应的连接管理器
                //取得返回结果
//            	FriendsAPI fApi=new FriendsAPI(oAuth.getOauthVersion());
            	if ("yes".equals(WeiboConfig.getValue("image"))) {
            		response=tAPI.addPic(oAuth, format, context, clientip,picname);
				}else {
					response=tAPI.add(oAuth, format, context, clientip);
				}
                     // json数据使用
                     // response的结果可能是这样，{"data":{"id":"90221131024999","time":1333002978},"errcode":0,"msg":"ok","ret":0}
                     // 下面的代码将取出 id 的对应值，并赋予 reid
                    System.out.println("response = "+response);
                    JSONObject responseJsonObject;
                    JSONObject dataJsonObject;
                    responseJsonObject= JSONObject.fromObject(response);
                    dataJsonObject=(JSONObject)responseJsonObject.get("data");
                    id=ids=reid=dataJsonObject.get("id").toString();//对后面用到的 reid 赋值
//                fApi.add(oAuth, format, "lgq652874007", fopenids);
                tAPI.shutdownConnection();//关闭连接管理器
//                fApi.shutdownConnection();
			} catch (Exception e) {
				System.out.println("error");
				e.printStackTrace();
			}
                

/*
*------------------------------------------ 微博相关测试例 end--------------------------------------------
*/  
		    
                      
		                

	}
	
	
}
