package com.lgq.getData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.lgq.common.DemoConstant;
import com.lgq.common.Util;
/**
 * 调用 taobao.item.update
 * 更新商品销售属性
 * @author Jessica
 *
 */
public class UpdateItem {
	/**
	 * 组装协议参数
	 * @param sessionKey
	 * @param iid 商品的iid
	 * @param propertyAlias 颜色属性的别名
	 * @return
	 */
	private static String createRequestParam(String sessionKey,String iid,String propertyAlias) {
		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
		// 组装协议参数。
		apiparamsMap.put("method", "taobao.item.update");
		apiparamsMap.put("app_key", DemoConstant.APP_KEY);
		apiparamsMap.put("timestamp", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(new Date()));
		apiparamsMap.put("format", "xml");
		apiparamsMap.put("session", sessionKey);
		apiparamsMap.put("v", "1.0");
		// 组装应用参数
		apiparamsMap.put("iid", iid);
		apiparamsMap.put("property_alias", propertyAlias);
		
		// 获得签名。第二个参数为分配给您的APP_SECRET
		String sign = Util.sign(apiparamsMap, DemoConstant.APP_SERCET);
		// 组装协议参数sign
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
	 * 调用api，更新商品信息
	 * @param sessionKey
	 * @param iid
	 * @param propertyAlias
	 */
	public static void updateItem(String sessionKey,String iid,String propertyAlias) {
		Util.getResult(DemoConstant.SANDBOX_URL,createRequestParam(sessionKey,iid,propertyAlias));
	}
}
