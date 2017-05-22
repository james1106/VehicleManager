package com.xpizza.vclemgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 行驶记录
 */
@Entity
@Table(name = "driverHistory")
public class DriverHistory extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = 3685959904427425759L;

	/** 驾驶员 */
	@Column(name = "driver", nullable = false, length = 32)
	private String driver;

	/** 驾驶车辆 */
	@ManyToOne
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;

	/** 驾车时间 */
	@Column(name = "timeDrive", nullable = false)
	private Date timeDrive;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getTimeDrive() {
		return timeDrive;
	}

	public void setTimeDrive(Date timeDrive) {
		this.timeDrive = timeDrive;
	}

	@Override
	public String toString() {
		return "DriverHistory [driver=" + driver + ", vehicle=" + vehicle + ", timeDrive=" + timeDrive + "]";
	}

}
