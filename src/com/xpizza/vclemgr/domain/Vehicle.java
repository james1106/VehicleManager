package com.xpizza.vclemgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 用户
 */
@Entity
@Table(name = "sys04_vehicle")
public class Vehicle extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = -7672670957807844401L;

	/** 车辆编号 */
	@Column(name = "code", nullable = false, length = 32)
	private String code;

	/** 车辆简历 */
	/*
	@Column(name = "vehicleCV", nullable = false, length = 64)
	private String vehicleCV;
	*/

	/** 行驶里程 */
	@Column(name = "mileage", nullable = false, length = 128)
	private String mileage;

	/** 随车附件 */
	@Column(name = "attachments", nullable = true, length = 128)
	private String attachments;

	/** 单车供应证 */
	@Column(name = "supplyBookPath", nullable = true, length = 64)
	private String supplyBookPath;

	/** 固定的驾驶员 */
	@Column(name = "driver", nullable = false, length = 32)
	private String driver;

	/** 车辆状态 */
	@ManyToOne
	@JoinColumn(name = "VehicleStatusId")
	private VehicleStatus vehicleStatus;

	/** 录入时间 */
	@Column(name = "timeCreate", nullable = false)
	private Date timeCreate;

	/** 上次维修/报废时间 */
	@Column(name = "timeRepair")
	private Date timeRepair;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public String getSupplyBookPath() {
		return supplyBookPath;
	}

	public void setSupplyBookPath(String supplyBookPath) {
		this.supplyBookPath = supplyBookPath;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Date getTimeRepair() {
		return timeRepair;
	}

	public void setTimeRepair(Date timeRepair) {
		this.timeRepair = timeRepair;
	}

	@Override
	public String toString() {
		return "Vehicle [code=" + code + ", mileage=" + mileage + ", attachments=" + attachments + ", supplyBookPath="
				+ supplyBookPath + ", driver=" + driver + ", vehicleStatus=" + vehicleStatus + ", timeCreate="
				+ timeCreate + ", timeRepair=" + timeRepair + "]";
	}

}
