package com.xpizza.core.mvc.persistence;

/**
 * 排序类型
 */
public enum OrderType {

	ASC("ASC", "正序"), DESC("DESC", "倒序"), DEFAULT("", "系统默认排序");

	private String value;

	private String comment;

	public String getValue() {
		return value;
	}

	public String getComment() {
		return comment;
	}

	private OrderType(String value, String comment) {
		this.value = value;
		this.comment = comment;
	}

}
