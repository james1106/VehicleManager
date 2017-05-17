package com.xpizza.core.mvc.persistence;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.xpizza.core.cache.CacheUtil;

/**
 * 数据库表对象
 */
public class Table {

	/** 表命名空间 */
	private String schema = (String) CacheUtil.getCache("tablename.schema.default");

	/** 表名 */
	private String childName;

	public String getSchema() {
		return schema;
	}

	public String getChildName() {
		return childName;
	}

	public Table(String childName) {
		super();
		this.childName = childName;
	}

	public Table(String schema, String childName) {
		super();
		this.schema = schema;
		this.childName = childName;
	}

	@Override
	public String toString() {
		return "Table [schema=" + schema + ", childName=" + childName + "]";
	}

	/**
	 * 得到表名
	 */
	public String getName() {
		Assert.notNull(childName);
		if (StringUtils.isEmpty(schema)) {
			return childName;
		} else {
			return schema + "." + childName;
		}
	}

}
