package com.coffee.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密解密类（固定算法和密钥）
 * 
 * @version 创建时间：2009-8-12 上午10:30:24
 */
public class EncrytUnEncrypt {

	/**
	 * 加密算法
	 */
	private static String ALGORITHM = "Blowfish";

	/**
	 * 密钥
	 */
	private static byte[] KEY = "zghywlsp12345678".getBytes();

	/**
	 * 将字节数组转换成十六进制字符串
	 * 
	 * @param buf
	 *            需要转换成十六进制字符串的字节数组
	 * @return 十六进制字符串
	 */
	private static String Bytes2Hex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);

		for (int i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	/**
	 * 将十六进制字符串转换成字节数组
	 * 
	 * @param hexStr
	 *            十六进制字符串
	 * @return 字节数组
	 */
	private static byte[] Hex2Bytes(String hexStr) {
		byte[] b = new byte[hexStr.length() / 2];
		int pos = 0;
		char[] achar = hexStr.toCharArray();
		for (int i = 0; i < b.length; i++) {
			pos = i * 2;
			b[i] = (byte) ((parseChar(achar[pos]) << 4) | parseChar(achar[pos + 1]));
		}
		return b;
	}

	/**
	 * 处理字符得到字符在十六进制中的序号
	 * 
	 * @param c
	 *            字符
	 * @return 字符在十六进制中1-F的序号
	 */
	private static int parseChar(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;

	}

	/**
	 * 将输入字节数组进行加密，得到加密后的十六进制字符串
	 * 
	 * @param input
	 *            要进行加密的字节数组
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encode(byte[] input) throws Exception {
		try {
			SecretKey sk = new SecretKeySpec(KEY, ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, sk);
			byte[] cipherByte = cipher.doFinal(input);

			// return cipherByte;
			return Bytes2Hex(cipherByte);
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 将加密后的字符串解密成原来的样子
	 * 
	 * @param input
	 *            已经加密的十六进制字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decode(String input) throws Exception {
		if (input == null || input.length() == 0) {
			return null;
		}
		try {
			byte[] bin = Hex2Bytes(input);
			SecretKey sk = new SecretKeySpec(KEY, ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, sk);
			byte[] clearByte = cipher.doFinal(bin);

			// return clearByte;
			return new String(clearByte);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
