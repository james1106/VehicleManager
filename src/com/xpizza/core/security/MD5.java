package com.xpizza.core.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密算法之MD5
 */
public class MD5 {

	/**
	 * md5加密 加密规则:先使用md5加密一次，再对已加密的字符串取前16位再加密一次.然后再md5一次(升级版)
	 */
	public static String getMD5CodePlus(String string) {
		return getMD5Code(getMD5Code(getMD5Code(string).substring(0, 16)));
	}

	/**
	 * 普通MD5加密
	 */
	public static String getMD5Code(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

}
