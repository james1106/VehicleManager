package com.xpizza.core.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.xpizza.core.util.PropsUtil;
import com.xpizza.vclemgr.constant.Constant;

/**
 * @Description: 缓存工厂
 */
public class CacheFactory {

	/**
	 * factory
	 */
	private volatile static CacheFactory cacheFatory;

	private CacheFactory() {
	}

	/**
	 * get instance
	 * 
	 * @return
	 */
	public static CacheFactory getInstance() {
		if (cacheFatory == null) {
			synchronized (CacheFactory.class) {
				if (cacheFatory == null) {
					cacheFatory = new CacheFactory();
				}
			}
		}
		return cacheFatory;
	}

	/**
	 * static map
	 */
	private static Map<String, Object> factoryMap = new HashMap<>();

	/**
	 * contain a key
	 * 
	 * @param key
	 * @return
	 */
	public boolean containsKey(String key) {
		return factoryMap.containsKey(key);
	}

	/**
	 * get object by key
	 * 
	 * @param key
	 * @return
	 */
	public Object getOne(String key) {
		return factoryMap.get(key);
	}

	/**
	 * get all values
	 * 
	 * @return
	 */
	public Collection<Object> getAll() {
		return factoryMap.values();
	}

	/**
	 * add a new key-value
	 * 
	 * @param key
	 * @param object
	 */
	public void putOne(String key, Object object) {
		if (containsKey(key)) {
			remove(key);
		}
		factoryMap.put(key, object);
	}

	/**
	 * 
	 * @param srcMap
	 */
	public void putAll(Map<String, Object> srcMap) {
		factoryMap.putAll(srcMap);
	}

	/**
	 * remove a key-value by key
	 * 
	 * @param key
	 */
	public void remove(String key) {
		factoryMap.remove(key);
	}

	public void init() {
		for (String fileName : Constant.PROPERTIES_FILENAMES) {
			loadPropsIntoMemory(fileName);
		}
	}

	private void loadPropsIntoMemory(String fileName) {
		Map<String, String> properties = PropsUtil.getProperties(fileName);
		Iterator<String> it = properties.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String val = properties.get(key);
			putOne(key, val);
		}
	}

	public void reload() {
	}

	public void clear() {
		factoryMap.clear();
	}

	public Map<String, Object> getMappings() {
		Map<String, Object> map = new HashMap<>();
		map.putAll(factoryMap);
		return map;
	}

}