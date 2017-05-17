package com.xpizza.core.util;

import java.util.Map;

import com.xpizza.core.lang.StringUtil;
import com.xpizza.core.lang.exception.EmptyDataException;

/**
 * 断言
 */
public abstract class Asserts {

	/**
	 * 
	 * @Title: isTrue
	 * @Description: 断言这是一个肯定结果
	 * @param expression
	 * @param message
	 * @return: void
	 */
	public static void isTrue(boolean expression, String reason) {
		if (!expression) {
			throw new IllegalArgumentException(reason);
		}
	}

	/**
	 * 
	 * @Title: isNotEmpty
	 * @Description: 断言字符串不为空
	 * @param string
	 * @param reason
	 * @return: void
	 */
	public static void isNotEmpty(String string, String reason) {
		if (StringUtil.isEmpty(string)) {
			throw new EmptyDataException(reason);
		}
	}

	/**
	 * 
	 * @Title: isNotNull
	 * @Description: 断言对象为空
	 * @param object
	 * @param reason
	 * @return: void
	 */
	public static void isNotNull(Object object, String reason) {
		if (object == null) {
			throw new NullPointerException(reason);
		}
	}

	/**
	 * 
	 * @Title: containsKey
	 * @Description: 断言Map包含元素
	 * @param map
	 * @param key
	 * @param reason
	 * @return: void
	 */
	public static void containsKey(Map<?, ?> map, Object key, String reason) {
		isTrue(map.containsKey(key), reason);
	}

	/**
	 * 
	 * @Title: doesNotContainsKey
	 * @Description: 断言Map不包含元素
	 * @param map
	 * @param key
	 * @param reason
	 * @return: void
	 */
	public static void doesNotContainsKey(Map<?, ?> map, Object key, String reason) {
		isTrue(!map.containsKey(key), reason);
	}

}
