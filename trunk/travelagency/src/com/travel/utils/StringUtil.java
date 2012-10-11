package com.travel.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	
	
	public static String transformSolrMetacharactor(String input){
        StringBuffer sb = new StringBuffer();
        String regex = "[+\\-&|!(){}\\[\\]^\"~*?:(\\)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()){
            matcher.appendReplacement(sb, "\\\\"+matcher.group());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
	/**
	 * 从请求中获取注册ip地址
	 * @param request
	 * @return
	 */
	private static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 从URL中截取主域名
	 * @param url
	 * @return
	 */
	public static String getDomain(String url) {
		if (StringUtils.isEmpty(url)) {
			logger.error("获取平台来源出现异常");
			return "未知";
		}
		int len1 = url.indexOf(":") + 3;
		int len = url.indexOf("/", len1);
		String result = url.substring(len1, len);
		if (StringUtils.isEmpty(result)) {
			logger.error("获取平台来源出现异常");
			return "未知";
		}
		return result;
	}
	/**
	 * 从request中获取相关的参数并且trim和过滤；
	 * @param req
	 * @param param
	 * @return
	 */
	public static String getParameterAndTrim(HttpServletRequest req,
			String param) {
		String result = req.getParameter(param);
		return result == null ? "" : result.trim();
	}
	public static String getIPPosition(HttpServletRequest req){
		String ip=getIpAddr(req);
		HttpClient client=new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://webservice.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx/getCountryCityByIp?theIpAddress="+ip);
		String tmp="未知";
		try {
				HttpResponse response = client.execute(httpget);
				SAXReader saxReader = new SAXReader();
				Document document= saxReader.read(response.getEntity().getContent());
				Element element=document.getRootElement();
				Element e=(Element)element.elements().get(1);
				tmp=e.getTextTrim();
			} catch (Exception e) {
				logger.error("获取用户ip地址失败");
			}
			return ip+"-"+tmp;
	}

}
