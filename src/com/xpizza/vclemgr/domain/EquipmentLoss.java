package com.xpizza.vclemgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 器材消耗
 */
@Entity
@Table(name = "equipmentLoss")
public class EquipmentLoss extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = -5580982053957140748L;

	/** 器材 */
	@ManyToOne
	@JoinColumn(name = "rquipmentId")
	private Equipment rquipment;

	@Column(name = "timeBroken", nullable = false)
	private Date timeBroken;

	public Equipment getRquipment() {
		return rquipment;
	}

	public void setRquipment(Equipment rquipment) {
		this.rquipment = rquipment;
	}

	public Date getTimeBroken() {
		return timeBroken;
	}

	public void setTimeBroken(Date timeBroken) {
		this.timeBroken = timeBroken;
	}

	@Override
	public String toString() {
		return "EquipmentLoss [rquipment=" + rquipment + ", timeBroken=" + timeBroken + "]";
	}

}
