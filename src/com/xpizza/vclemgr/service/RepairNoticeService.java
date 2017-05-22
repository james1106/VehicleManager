package com.xpizza.vclemgr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.core.lang.StringUtil;
import com.xpizza.vclemgr.dao.RepairBillDao;
import com.xpizza.vclemgr.dao.RepairNoticeDao;
import com.xpizza.vclemgr.dao.UserDao;
import com.xpizza.vclemgr.dao.VehicleDao;
import com.xpizza.vclemgr.dao.VehicleStatusDao;
import com.xpizza.vclemgr.domain.RepairNotice;
import com.xpizza.vclemgr.domain.User;
import com.xpizza.vclemgr.domain.Vehicle;

@Service
public class RepairNoticeService {

	@Resource
	private RepairNoticeDao repairNoticeDao;

	@Resource
	private RepairBillDao repairBillDao;

	@Resource
	private UserDao userDao;

	@Resource
	private VehicleDao vehicleDao;

	@Resource
	private VehicleStatusDao vehicleStatusDao;

	public List<RepairNotice> findLike(String userNoticeName, String vehicleCode, String userRepairName) {
		List<User> userNotices = userDao.findByUsernameLike("%" + userNoticeName + "%");
		List<Vehicle> vehicles = vehicleDao.findByCodeLike("%" + vehicleCode + "%");
		List<Long> userNoticeIds = new ArrayList<>();
		List<Long> vehicleIds = new ArrayList<>();
		for (User user : userNotices) {
			userNoticeIds.add(user.getId());
		}
		for (Vehicle vehicle : vehicles) {
			vehicleIds.add(vehicle.getId());
		}
		if (StringUtil.isEmpty(userRepairName)) {
			return repairNoticeDao.findByUserNoticeIdInAndVehicleIdIn(userNoticeIds, vehicleIds);
		}
		List<User> userRepairs = userDao.findByUsernameLike("%" + userRepairName + "%");
		List<Long> userRepairIds = new ArrayList<>();
		for (User user : userRepairs) {
			userRepairIds.add(user.getId());
		}
		return repairNoticeDao.findByUserNoticeIdInAndVehicleIdInAndUserRepairIdIn(userNoticeIds, vehicleIds,
				userRepairIds);
	}

	public void accept(List<Long> ids, Long userId) {
		User user = userDao.findOne(userId);
		List<RepairNotice> notices = repairNoticeDao.findByIdIn(ids);
		for (RepairNotice each : notices) {
			each.setUserRepair(user);
			each.setTimeRepair(new Date());
			repairNoticeDao.save(each);
		}
	}

}
