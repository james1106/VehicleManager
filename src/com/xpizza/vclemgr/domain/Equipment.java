package com.xpizza.vclemgr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 器材
 */
@Entity
@Table(name = "equipment")
public class Equipment extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = -5580982053957140748L;

	/** 器材名称 */
	@Column(name = "name", nullable = false, length = 32)
	private String name;

	@Column(name = "stock", nullable = false)
	private Long stock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Equipment [name=" + name + ", stock=" + stock + "]";
	}

}
