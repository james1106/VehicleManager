package com.xpizza.vclemgr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

@Entity
@Table(name = "sys03_vehicleStatus")
public class VehicleStatus extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = 6994704724192427061L;

	/** 车辆状态 */
	@Column(name = "status", nullable = false, length = 1)
	private String status;

	/** 车辆状态说明 */
	@Column(name = "comment", nullable = false, length = 32)
	private String comment;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "VehicleStatus [status=" + status + ", comment=" + comment + "]";
	}

}
