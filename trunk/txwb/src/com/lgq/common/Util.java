package com.lgq.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import sun.misc.BASE64Decoder;
/**
 * 工具类
 * 1、给传递的参数加密
 * 2、模拟post请求，传递参数，得到返回结果
 * 3、从回调URL的参数中获得visitor_nick值，即nick
 * @author TOP
 *
 */
public class Util {
	/*
	 * 二行制转字符串
	 */
	private static String byte2hex(byte[] b) {

		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	/*
	 * 把经过BASE64编码的字符串转换为Map对象
	 */
	public static Map<String, String> convertBase64StringtoMap(String str) {

		if (str == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		String keyvalues = null;
		try {
			keyvalues = new String(decoder.decodeBuffer(str));
		} catch (IOException e) {
			return null;
		}
		if (keyvalues == null || keyvalues.length() == 0)
			return null;
		String[] keyvalueArray = keyvalues.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String keyvalue : keyvalueArray) {
			String[] s = keyvalue.split("=");
			if (s == null || s.length != 2)
				return null;
			map.put(s[0], s[1]);
		}
		return map;
	}

	/*
	 * 签名方法，用于生成签名。
	 * 
	 * @param params 传给服务器的参数
	 * 
	 * @param secret 分配给您的APP_SECRET
	 */
	public static String sign(TreeMap<String, String> params, String secret) {

		String result = null;
		if (params == null)
			return result;
		Iterator<String> iter = params.keySet().iterator();
		StringBuffer orgin = new StringBuffer(secret);
		while (iter.hasNext()) {
			String name = (String) iter.next();
			orgin.append(name).append(params.get(name));
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			result = byte2hex(md.digest(orgin.toString().getBytes("utf-8")));
		} catch (Exception ex) {
			throw new java.lang.RuntimeException("sign error !");
		}
		return result;
	}

	/**
	 * 模拟post请求，调用api，传递参数，获得结果
	 * @param urlStr 请求的url
	 * @param content 传递的参数
	 * @return 调用api获得返回结果
	 */
	public static String getResult(String urlStr, String content) {
		URL url = null;
		HttpURLConnection connection = null;

		try {
			//建立连接
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();

			//传递参数
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			//用utf-8的编码方式传递参数，否则中文会出现乱码
			out.write(content.getBytes("utf-8"));
			out.flush();
			out.close();

			//获得返回结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection
					.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
	
	/**
	 * 根据回调url中的top_parameters获得nick
	 * @param top_parameters 回调url中的参数
	 * @return nick
	 */
    public static String ParametersName(String top_parameters){

        String nick=null; 
        Map<String, String> map= convertBase64StringtoMap(top_parameters); 
        Iterator keyValuePairs = map.entrySet().iterator(); 
        for (int i = 0; i < map.size(); i++) 
        { 
	        Map.Entry entry = (Map.Entry) keyValuePairs.next(); 
	        Object key = entry.getKey(); 
	        Object value = entry.getValue(); 
	        if(key.equals("visitor_nick")){
	
	            nick=(String) value; 
	        } 
        } 

        return nick; 

    }
}
