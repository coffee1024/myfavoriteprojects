package com.coffee.util;

/**
 * @Title IP常用类
 * @CrTime 2010-4-15 下午09:39:49
 * @Version 1.0
 */
public class IPUtil {
	/**
	 * 判斷是否有效的ip地址
	 * 
	 * @param ip
	 *            要判斷的ip
	 * @param starEnabled
	 *            ip是否可以用*匹配
	 * @return 匹配成功返回true
	 */
	public static boolean isValidIPv4(String ip, boolean starEnabled) {
		if (ip == null || ip.length() == 0) {
			return false;
		}
		String REGX_IP = null;
		if (starEnabled) {
			REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d|\\*)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d|\\*)";
		} else {
			REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
		}
		return ip.matches(REGX_IP);
	}

	/**
	 * 判断ip是否在指定范围
	 * 
	 * @param ip
	 *            要判断的ip
	 * @param sections
	 *            ip范围，可以是ip段10.2.3.*-10.4.5.*，也可以是12.34.56.*，中间用;隔开
	 * @return 如果在范围内则返回true
	 */
	public static boolean isIpInSection(String ip, String sections) {
		if (ip == null || ip.length() == 0) {
			return false;
		}
		if (!isValidIPv4(ip, false)) {
			return false;
		}
		if (sections == null || sections.length() == 0) {
			return false;
		}

		boolean bResult = false;
		String[] ipSection = sections.split(";");
		String[] ipAddress = ip.split("\\.");
		String[] ipStart = null;
		String[] ipEnd = null;
		long lStart = 0L, lEnd = 0L, lIp = 0L;

		for (int i = 0; i < ipSection.length; i++) {
			lStart = 0L;
			lEnd = 0L;
			lIp = 0L;
			String[] ips = ipSection[i].split("-");

			// 如果只有一个ip
			if (ips.length == 1) {
				if (!isValidIPv4(ips[0], true)) {
					continue;
				}
				ipStart = ips[0].split("\\.");
				ipEnd = ips[0].split("\\.");
			} else {
				// 如果是一个ip段，如10.23.45.*-12.34.56.*
				if (ips.length == 2) {
					if (!isValidIPv4(ips[0], true)
							|| !isValidIPv4(ips[1], true)) {
						continue;
					}
					ipStart = ips[0].split("\\.");
					ipEnd = ips[1].split("\\.");
				}
			}

			// 转换ip为长整型进行判断
			for (int j = 0; j < 4; j++) {
				lIp = ("*".equals(ipAddress[j])) ? lIp << 8 : lIp << 8
						| Integer.parseInt(ipAddress[j]);
				lStart = ("*".equals(ipStart[j])) ? lStart << 8 : lStart << 8
						| Integer.parseInt(ipStart[j]);
				lEnd = ("*".equals(ipEnd[j])) ? lEnd << 8 | 255 : lEnd << 8
						| Integer.parseInt(ipEnd[j]);
			}
			if (lStart > lEnd) {
				long t = lStart;
				lStart = lEnd;
				lEnd = t;
			}
			if (lStart <= lIp && lIp <= lEnd) {
				bResult = true;
				break;
			}
		}
		return bResult;
	}
}
