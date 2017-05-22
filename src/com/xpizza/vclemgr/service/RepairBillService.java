package com.xpizza.vclemgr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.core.lang.StringUtil;
import com.xpizza.vclemgr.constant.RepairStatus;
import com.xpizza.vclemgr.dao.EquipmentDao;
import com.xpizza.vclemgr.dao.EquipmentLossDao;
import com.xpizza.vclemgr.dao.RepairBillDao;
import com.xpizza.vclemgr.dao.UserDao;
import com.xpizza.vclemgr.dao.VehicleDao;
import com.xpizza.vclemgr.dao.VehicleStatusDao;
import com.xpizza.vclemgr.domain.Equipment;
import com.xpizza.vclemgr.domain.EquipmentLoss;
import com.xpizza.vclemgr.domain.RepairBill;
import com.xpizza.vclemgr.domain.User;
import com.xpizza.vclemgr.domain.Vehicle;

@Service
public class RepairBillService {

	@Resource
	private RepairBillDao repairBillDao;

	@Resource
	private VehicleDao vehicleDao;

	@Resource
	private VehicleStatusDao vehicleStatusDao;

	@Resource
	private UserDao userDao;

	@Resource
	private EquipmentDao equipmentDao;

	@Resource
	private EquipmentLossDao equipmentLossDao;

	public List<RepairBill> findLike(String userRepair, String vehicleCode) {
		List<Vehicle> vehicles = vehicleDao.findByCodeLike("%" + vehicleCode + "%");
		List<Long> vehicleIds = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			vehicleIds.add(vehicle.getId());
		}
		List<User> userRepairIds = userDao.findByUsernameLike("%" + userRepair + "%");
		List<Long> userIds = new ArrayList<>();
		for (User user : userRepairIds) {
			userIds.add(user.getId());
		}
		return repairBillDao.findByUserRepairIdInAndVehicleIdIn(userIds, vehicleIds);
	}

	public void save(String flagRepair, String equipment, String vehicleId, String commentRepair, Long userId) {
		RepairBill repairBill = new RepairBill();
		Vehicle vehicle = vehicleDao.findOne(Long.parseLong(vehicleId.trim()));
		vehicle.setVehicleStatus(vehicleStatusDao.findByStatus(RepairStatus.OK));
		repairBill.setVehicle(vehicle);
		vehicleDao.save(vehicle);
		repairBill.setUserRepair(userDao.getOne(userId));
		repairBill.setFlagRepair(StringUtil.getBoolValue(flagRepair));
		repairBill.setCommentRepair(commentRepair);
		repairBill.setTimeRepair(new Date());
		repairBillDao.save(repairBill);
		// 填写器材消耗
		String[] eqIdStrArr = equipment.split(",");
		List<Long> eqIds = new ArrayList<>();
		for (String eqId : eqIdStrArr) {
			eqIds.add(Long.parseLong(eqId));
		}
		// 器材消耗统计
		List<Equipment> eqList = equipmentDao.findByIdIn(eqIds);
		for (Equipment eq : eqList) {
			EquipmentLoss eqLoss = new EquipmentLoss();
			eqLoss.setRquipment(eq);
			eqLoss.setTimeBroken(new Date());
			equipmentLossDao.save(eqLoss);
		}
	}

}
