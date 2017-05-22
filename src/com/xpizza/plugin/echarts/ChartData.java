package com.xpizza.plugin.echarts;

public class ChartData {

	private String name;

	private String Value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	@Override
	public String toString() {
		return "ChartData [name=" + name + ", Value=" + Value + "]";
	}

}
