package com.xpizza.vclemgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 维修人员接到通知单后,创建维修单
 */
@Entity
@Table(name = "repairBill")
public class RepairBill extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = 9096438596977115575L;

	/** 维修人员 */
	@ManyToOne
	@JoinColumn(name = "userRepairId")
	private User userRepair;

	/** 维修车辆 */
	@ManyToOne
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;

	/** 是否修好(修好之后将车辆状态设为OK,如果无法维修好,则设为BAD) */
	@Column(name = "flagRepair")
	private boolean flagRepair;

	/** 维修时间 */
	@Column(name = "timeRepair", nullable = false)
	private Date timeRepair;

	/** 维修说明 */
	@Column(name = "commentRepair", length = 128)
	private String commentRepair;

	public User getUserRepair() {
		return userRepair;
	}

	public void setUserRepair(User userRepair) {
		this.userRepair = userRepair;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public boolean isFlagRepair() {
		return flagRepair;
	}

	public void setFlagRepair(boolean flagRepair) {
		this.flagRepair = flagRepair;
	}

	public Date getTimeRepair() {
		return timeRepair;
	}

	public void setTimeRepair(Date timeRepair) {
		this.timeRepair = timeRepair;
	}

	public String getCommentRepair() {
		return commentRepair;
	}

	public void setCommentRepair(String commentRepair) {
		this.commentRepair = commentRepair;
	}

	@Override
	public String toString() {
		return "RepairBill [userRepair=" + userRepair + ", vehicle=" + vehicle + ", flagRepair=" + flagRepair
				+ ", timeRepair=" + timeRepair + ", commentRepair=" + commentRepair + "]";
	}

}
