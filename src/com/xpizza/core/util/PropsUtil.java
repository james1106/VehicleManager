package com.xpizza.core.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 属性文件工具包
 */
public class PropsUtil {

	/**
	 * 取出properties文件所有键值对
	 */
	public static Map<String, String> getProperties(String fileName) {
		Map<String, String> map = new HashMap<String, String>();
		Properties properties = new Properties();
		try {
			properties.load(PropsUtil.class.getClassLoader().getResourceAsStream(fileName));
			Enumeration<?> en = properties.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String value = properties.getProperty(key);
				map.put(key, value);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据properties文件与key取值
	 */
	public static String getPropertiesByKey(String fileName, String key) {
		Properties properties = new Properties();
		try {
			properties.load(PropsUtil.class.getClassLoader().getResourceAsStream(fileName));
			return properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
