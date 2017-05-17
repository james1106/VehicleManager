package com.xpizza.core.cache;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xpizza.core.lang.StringUtil;

/**
 * Util for cacher
 *
 */
public class CacheUtil {

	static final Logger logger = LoggerFactory.getLogger(CacheUtil.class);

	/**
	 * 获取本地内存中的一项配置
	 */
	public static Object getCache(String key) {
		return CacheFactory.getInstance().getOne(key);
	}

	public static boolean isLinuxMode() {
		return StringUtil.getBoolValue((String) getCache("os.isLinux"));
	}

	/**
	 * 获取系统根目录
	 */
	public static String getRootPath() {
		String rootPath = null;
		if (isLinuxMode()) {
			rootPath = (String) getCache("path.root.linux");
		} else {
			rootPath = (String) getCache("path.root.windows");
		}
		if (StringUtils.isNotEmpty(rootPath)) {
			logger.error("The root path is empty!Please set it in global.properties.");
		}
		return rootPath;
	}

}
