
package com.xpizza.core.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据:由后台组织好传递给前台
 */
public class Data {

	/** Model Map */
	private Map<String, Object> dataMap = new HashMap<>();

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public Data() {
		dataMap.put("isOk", true);
		dataMap.put("msg", "操作成功");
	}

	public void add(String modelName, Object modelObject) {
		dataMap.put(modelName, modelObject);
	}

	public void addAll(Map<String, Object> dataMap) {
		this.dataMap.putAll(dataMap);
	}

	/** 设置错误信息 */
	public void setError(String message) {
		dataMap.put("isOk", false);
		dataMap.put("msg", message);
	}

}
