package com.xpizza.vclemgr.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.vclemgr.constant.RepairStatus;
import com.xpizza.vclemgr.dao.VehicleDao;
import com.xpizza.vclemgr.dao.VehicleStatusDao;
import com.xpizza.vclemgr.domain.Vehicle;

@Service
public class VehicleService {

	@Resource
	private VehicleDao vehicleDao;

	@Resource
	private VehicleStatusDao vehicleStatusDao;

	public List<Vehicle> findAll() {
		return vehicleDao.findAll();
	}

	public void save(String code, String mileage, String attachments, String driver) {
		Vehicle vehicle = new Vehicle();
		vehicle.setCode(code);
		vehicle.setMileage(mileage);
		vehicle.setAttachments(attachments);
		vehicle.setDriver(driver);
		vehicle.setVehicleStatus(vehicleStatusDao.findByStatus(RepairStatus.OK));
		vehicle.setTimeCreate(new Date());
		vehicleDao.save(vehicle);
	}

	public void remove(List<Long> ids) {
		vehicleDao.deleteByIdIn(ids);
	}

	public List<Vehicle> findByContition(String code, String driver) {
		return vehicleDao.findByCodeLikeAndDriverLike("%" + code + "%", "%" + driver + "%");
	}

	public Vehicle findOne(String vehicleId) {
		return vehicleDao.findOne(Long.parseLong(vehicleId));
	}

}
