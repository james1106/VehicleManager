package com.xpizza.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 网络工具
 */
public class NetUtil {

	/** 获取客户端IP */
	public static String getRemoteAddr(HttpServletRequest request) {
		String[] headNames = { "X-FORWARDED-FOR", "Proxy-Client-IP", "WL-Proxy-Client-IP", "x-real-ip" };
		String ip = null;
		for (String headName : headNames) {
			ip = request.getHeader(headName);
			if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

	/** 调用接口,获取数据 */
	public static String openSimpleConnect(String uri) throws IOException {
		URL url = new URL(uri);
		URLConnection urlconn = url.openConnection();
		InputStream is = urlconn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = "";
		StringBuffer sb = new StringBuffer();
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		return sb.toString();
	}

}
