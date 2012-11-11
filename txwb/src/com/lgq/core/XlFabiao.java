package com.lgq.core;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimerTask;

import net.sf.json.JSONObject;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.model.PostParameter;
import weibo4j.model.Status;
import weibo4j.util.WeiboConfig;

import com.lgq.dao.PicDao;
import com.lgq.dao.WeiboDao;
import com.tencent.weibo.api.TAPI;

/**
 *@author  Administrator
 *@date    2012-6-14 下午4:24:25
 *@version 1.0
 **/
public class XlFabiao extends TimerTask {
	private  AccessToken at;
	private int i=1;
	private int j=1;
	public XlFabiao(AccessToken at){
		System.out.println("新建新浪定时任务");
		this.at=at;
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
        String picname="";
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
			context=context+"喜欢请收听@刘光强_java";
		}
        try {
            int pictotal=new PicDao().getCount();
        	Random random=new Random();
        	int tmp=random.nextInt(pictotal);
        	System.out.println(tmp+"--------");
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
        		Map<String, String> params = new HashMap<String, String>();
        		params.put("access_token", at.getAccessToken());
        		params.put("status", context);
        		if ("yes".equals(WeiboConfig.getValue("image"))) {
        			Map<String, byte[]> itemsMap = new HashMap<String, byte[]>();
        			itemsMap.put("pic", HttpUtil.readFromURL(picname));
        			HttpUtil.postMethodRequestWithFile(HttpUtil.POST_WEIBO_URL_WITH_IMAGE, params, HttpUtil.header,itemsMap);
				}else {
					HttpUtil.postMethodRequestWithOutFile(HttpUtil.POST_WEIBO_URL_WITH_CONTENT, params, HttpUtil.header);
				}
			
            } catch (Exception e) {
				System.out.println("error");
				e.printStackTrace();
			}
                

/*
*------------------------------------------ 微博相关测试例 end--------------------------------------------
*/  
		    
                      
		                

	}
	
	
}
