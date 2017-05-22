package com.xpizza.vclemgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 报修单据
 */
@Entity
@Table(name = "repairApply")
public class RepairApply extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = -3420281399647620114L;

	/* ***1.报修*** */
	/** 单据申请人 */
	@ManyToOne
	@JoinColumn(name = "userApplyId")
	private User userApply;

	/** 报修车辆 */
	@ManyToOne
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;

	/** 报修说明 */
	@Column(name = "commentApply", length = 128)
	private String commentApply;

	/** 申请时间 */
	@Column(name = "timeApply", nullable = false)
	private Date timeApply;

	/* ***2.审批*** */
	/** 单据申请人 */
	@ManyToOne
	@JoinColumn(name = "userApproveId")
	private User userApprove;

	/** 是否通过(通过之后创建通知单) */
	@Column(name = "flagApprove")
	private boolean flagApprove;

	/** 通过时间 */
	@Column(name = "timeApprove")
	private Date timeApprove;

	public User getUserApply() {
		return userApply;
	}

	public void setUserApply(User userApply) {
		this.userApply = userApply;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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
		return "RepairApply [userApply=" + userApply + ", vehicle=" + vehicle + ", commentApply=" + commentApply
				+ ", timeApply=" + timeApply + ", userApprove=" + userApprove + ", flagApprove=" + flagApprove
				+ ", timeApprove=" + timeApprove + "]";
	}

}
