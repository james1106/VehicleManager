package com.xpizza.plugin.echarts;

/**
 * 图形数据
 */
public class ChartData {

	private String name;

	private int value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ChartData [name=" + name + ", value=" + value + "]";
	}

}
