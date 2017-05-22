package com.xpizza.vclemgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 维修通知单
 */
@Entity
@Table(name = "repairNotice")
public class RepairNotice extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = -88838566886497167L;

	/* ***1.报修*** */
	/** 通知人 */
	@ManyToOne
	@JoinColumn(name = "userNoticeId")
	private User userNotice;

	/** 报修车辆 */
	@ManyToOne
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;

	/** 通知说明 */
	@Column(name = "commentNotice", length = 128)
	private String commentNotice;

	/** 通知时间 */
	@Column(name = "timeNotice", nullable = false)
	private Date timeNotice;

	/** 维修人 */
	@ManyToOne
	@JoinColumn(name = "userRepair", nullable = true)
	private User userRepair;

	/** 接收通知 */
	@Column(name = "timeRepair", nullable = true)
	private Date timeRepair;

	public User getUserNotice() {
		return userNotice;
	}

	public void setUserNotice(User userNotice) {
		this.userNotice = userNotice;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getCommentNotice() {
		return commentNotice;
	}

	public void setCommentNotice(String commentNotice) {
		this.commentNotice = commentNotice;
	}

	public Date getTimeNotice() {
		return timeNotice;
	}

	public void setTimeNotice(Date timeNotice) {
		this.timeNotice = timeNotice;
	}

	public User getUserRepair() {
		return userRepair;
	}

	public void setUserRepair(User userRepair) {
		this.userRepair = userRepair;
	}

	public Date getTimeRepair() {
		return timeRepair;
	}

	public void setTimeRepair(Date timeRepair) {
		this.timeRepair = timeRepair;
	}

	@Override
	public String toString() {
		return "RepairNotice [userNotice=" + userNotice + ", vehicle=" + vehicle + ", commentNotice=" + commentNotice
				+ ", timeNotice=" + timeNotice + ", userRepair=" + userRepair + ", timeRepair=" + timeRepair + "]";
	}

}
