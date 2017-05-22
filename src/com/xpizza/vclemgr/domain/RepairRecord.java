package com.xpizza.vclemgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 维修记录
 */
@Entity
@Table(name = "repairRecord")
public class RepairRecord extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = -5897072703380482176L;

	/** 维修单 */
	@ManyToOne
	@JoinColumn(name = "repairBillId")
	private RepairBill repairBill;

	/** 报修器材 */
	@ManyToOne
	@JoinColumn(name = "equipmentId")
	private Equipment equipment;

	/** 记录时间 */
	@Column(name = "timeCreate", nullable = false)
	private Date timeCreate;

	public RepairBill getRepairBill() {
		return repairBill;
	}

	public void setRepairBill(RepairBill repairBill) {
		this.repairBill = repairBill;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	@Override
	public String toString() {
		return "RepairRecord [repairBill=" + repairBill + ", equipment=" + equipment + ", timeCreate=" + timeCreate
				+ "]";
	}

}
