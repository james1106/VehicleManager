package com.xpizza.core.io;

import java.io.File;
import java.io.IOException;

import com.xpizza.core.lang.StringUtil;

/**
 * 流操作工具
 */
public class IOUtil {

	/** 1KB */
	public static int ONE_KB = 1024;
	/** 1MB */
	public static int ONE_MB = ONE_KB * ONE_KB;
	/** 1GB */
	public static int ONE_GB = ONE_KB * ONE_MB;

	/** 目录不存在就创建 */
	public static void creDirIfNotExsit(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/** 文件不存在就创建 */
	public static void creFileIfNotExsit(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	/** 深度清理文件夹 */
	public static boolean clearFolder(String folderPath) {
		if (StringUtil.isEmpty(folderPath)) {
			return false;
		}
		File folderFile = new File(folderPath);
		if (!folderFile.exists()) {
			creDirIfNotExsit(folderPath);
			return true;
		}
		File[] fileList = folderFile.listFiles();
		for (File childFile : fileList) {
			if (childFile.isFile()) {
				if (!childFile.delete())
					return false;
			} else if (childFile.isDirectory()) {
				clearFolder(childFile.getPath());
				if (!childFile.delete())
					return false;
			}
		}
		return true;
	}

}
