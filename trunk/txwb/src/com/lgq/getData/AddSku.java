package com.lgq.getData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.lgq.common.DemoConstant;
import com.lgq.common.Util;
/**
 * 调用 taobao.item.sku.add
 * 添加sku
 * @author Jessica
 *
 */
public class AddSku {
	/**
	 * 组装请求参数
	 * @param sessionKey
	 * @param properties 属性值
	 * @param iid 商品的iid
	 * @return 组装好的请求参数
	 */
	private static String createRequestParam(String sessionKey,String properties,String iid){
		TreeMap<String,String> apiparamsMap = new TreeMap<String,String>();
		// 组装协议参数。
		apiparamsMap.put("method", "taobao.item.sku.add");
		apiparamsMap.put("app_key", DemoConstant.APP_KEY);
		apiparamsMap.put("timestamp", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()));
		apiparamsMap.put("session", sessionKey);
		apiparamsMap.put("format", "xml");
		apiparamsMap.put("v", "2.0");
		// 组装应用参数
		apiparamsMap.put("iid", iid);
		apiparamsMap.put("properties", properties);
		apiparamsMap.put("quantity", "30");
		apiparamsMap.put("price", "200.07");
		//生成签名
		String sign = Util.sign(apiparamsMap, DemoConstant.APP_SERCET);
		//组装协议参数sign
		apiparamsMap.put("sign", sign);
		
		StringBuilder param = new StringBuilder();
		for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<String, String> e = it.next();
			param.append("&").append(e.getKey()).append("=").append(
					e.getValue());
		}
		return param.toString().substring(1);
	}
	/**
	 * 调用api，获得结果
	 * @param sessionKey 
	 * @param properties 属性值
	 * @param iid 商品iid
	 */
	public static void addSku(String sessionKey,String properties,String iid) {
		//获得请求参数
		String paramStr = createRequestParam(sessionKey,properties,iid);
		//调用api获得结果
		Util.getResult(DemoConstant.SANDBOX_URL,paramStr);
	}
}
