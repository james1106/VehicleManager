package com.xpizza.core.lang;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/** 字符串处理工具 */
public class StringUtil {

	/** 判断字符串或者字符串数组是否为空 */
	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		if (object.toString().length() == 0) {
			return true;
		}
		if (object.getClass().isArray()) {
			return ((String[]) object).length == 0 ? true : false;
		}
		return false;
	}

	/** 判断字符串或字符串数组不为空 */
	public static boolean isNotEmpty(Object object) {
		return !isEmpty(object);
	}

	/** Object向下转String,为空时给默认值 */
	public static String toStringWithDefaultVal(Object object, String defaultValue) {
		return isEmpty(object) ? defaultValue : object.toString();
	}

	/** Object向下转String,为空时给"" */
	public static String toStringWithEmpty(Object object) {
		return toStringWithDefaultVal(object, "");
	}

	/** 字符串转boolean */
	public static boolean getBoolValue(Object object) {
		if (isEmpty(object)) {
			return false;
		}
		String boolString = object.toString();
		return "true".equalsIgnoreCase(boolString) || "1".equals(boolString);
	}

	/** 获取1个32位UUID */
	public static String get32UUID() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}

	/** 字符转"UTF-8",("iso-8859-1" | ...) */
	public static String toUTF8String(String str, String oldEncoding) throws UnsupportedEncodingException {
		return new String(str.getBytes(oldEncoding), "UTF-8");
	}

}
