package com.xpizza.vclemgr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 角色
 */
@Entity
@Table(name = "sys01_role")
public class Role extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = -8049406477656589096L;

	/** 角色名称 */
	@Column(name="name",nullable=false,length=32)
	private String name;

	/** 角色说明 */
	@Column(name="comment",nullable=true,length=128)
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", comment=" + comment + "]";
	}

}
