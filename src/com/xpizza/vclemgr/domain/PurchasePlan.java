package com.xpizza.vclemgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 购买计划
 */
@Entity
@Table(name = "purchasePlan")
public class PurchasePlan extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = -1894477280530510639L;

	/** 器材 */
	@ManyToOne
	@JoinColumn(name = "equipmentId")
	private Equipment equipment;

	/**
	 * 购买数量
	 */
	@Column(name = "num", nullable = false)
	private Long num;

	/** 单据申请人 */
	@ManyToOne
	@JoinColumn(name = "userApplyId")
	private User userApply;

	/** 购买说明 */
	@Column(name = "commentApply", length = 128)
	private String commentApply;

	/** 申请时间 */
	@Column(name = "timeApply", nullable = false)
	private Date timeApply;

	/* ***2.审批*** */
	/** 单据审核人 */
	@ManyToOne
	@JoinColumn(name = "userApproveId")
	private User userApprove;

	/** 是否通过(通过之后创建通知单) */
	@Column(name = "flagApprove")
	private boolean flagApprove;

	/** 通过时间 */
	@Column(name = "timeApprove")
	private Date timeApprove;

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public User getUserApply() {
		return userApply;
	}

	public void setUserApply(User userApply) {
		this.userApply = userApply;
	}

	public String getCommentApply() {
		return commentApply;
	}

	public void setCommentApply(String commentApply) {
		this.commentApply = commentApply;
	}

	public Date getTimeApply() {
		return timeApply;
	}

	public void setTimeApply(Date timeApply) {
		this.timeApply = timeApply;
	}

	public User getUserApprove() {
		return userApprove;
	}

	public void setUserApprove(User userApprove) {
		this.userApprove = userApprove;
	}

	public boolean isFlagApprove() {
		return flagApprove;
	}

	public void setFlagApprove(boolean flagApprove) {
		this.flagApprove = flagApprove;
	}

	public Date getTimeApprove() {
		return timeApprove;
	}

	public void setTimeApprove(Date timeApprove) {
		this.timeApprove = timeApprove;
	}

	@Override
	public String toString() {
		return "PurchasePlan [equipment=" + equipment + ", num=" + num + ", userApply=" + userApply + ", commentApply="
				+ commentApply + ", timeApply=" + timeApply + ", userApprove=" + userApprove + ", flagApprove="
				+ flagApprove + ", timeApprove=" + timeApprove + "]";
	}

}
