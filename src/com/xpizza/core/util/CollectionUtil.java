package com.xpizza.core.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** 集合工具 */
public class CollectionUtil {

	/** 判断Map是否为空 */
	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	/** 判断集合为空 */
	public static boolean isEmpty(Collection<?> c) {
		return c == null || c.isEmpty();
	}

	/** 集合变 String,以逗号分隔 */
	public static String getCollectionStr(Collection<?> c) {
		return getArrStr(c.toArray());
	}

	/**
	 * 数组变 String,以逗号分隔
	 */
	public static String getArrStr(Object[] arr) {
		if (arr.length == 0)
			return "";
		StringBuffer sb = new StringBuffer();
		for (Object each : arr) {
			sb.append(each).append(",");
		}
		String str = sb.toString();
		return str.substring(0, str.length() - 1);
	}

	/**
	 * 把元素是string的list转化为字符串(带单引号)
	 */
	public static String getStrWithSingleQuote(List<String> list) {
		StringBuffer result = new StringBuffer();
		for (String str : list) {
			result.append("'").append(str).append("',");
		}
		return result.substring(0, result.length() - 1);
	}

	/**
	 * 
	 * @Title: copyProps
	 * @Description: 复制属性
	 * @param targetMap
	 *            目标Map
	 * @param srcMap
	 *            源Map
	 * @param keys
	 *            被复制的属性
	 * @return: void
	 */
	public static void copyProps(Map<String, Object> targetMap, Map<String, Object> srcMap, String... keys) {
		for (String key : keys) {
			targetMap.put(key, srcMap.get(key));
		}
	}

	/**
	 * 剪切Map的K-V
	 */
	public static void cutProps(Map<String, Object> targetMap, Map<String, Object> srcMap, String... keys) {
		for (String key : keys) {
			targetMap.put(key, srcMap.get(key));
			srcMap.remove(key);
		}
	}

	/**
	 * 克隆Map的K-V
	 */
	public static Map<String, Object> cloneMap(Map<String, Object> srcMap) {
		Map<String, Object> targetMap = new HashMap<>();
		Iterator<String> it = srcMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			targetMap.put(key, srcMap.get(key));
		}
		return targetMap;
	}

	/**
	 * 合并多个数组
	 */
	public static <T> T[] concatAll(T[] first, @SuppressWarnings("unchecked") T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

}
