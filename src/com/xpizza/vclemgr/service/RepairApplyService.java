package com.xpizza.vclemgr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.core.lang.StringUtil;
import com.xpizza.vclemgr.constant.RepairStatus;
import com.xpizza.vclemgr.dao.RepairApplyDao;
import com.xpizza.vclemgr.dao.RepairNoticeDao;
import com.xpizza.vclemgr.dao.UserDao;
import com.xpizza.vclemgr.dao.VehicleDao;
import com.xpizza.vclemgr.dao.VehicleStatusDao;
import com.xpizza.vclemgr.domain.RepairApply;
import com.xpizza.vclemgr.domain.RepairNotice;
import com.xpizza.vclemgr.domain.User;
import com.xpizza.vclemgr.domain.Vehicle;

@Service
public class RepairApplyService {

	@Resource
	private RepairApplyDao repairApplyDao;

	@Resource
	private RepairNoticeDao repairNoticeDao;

	@Resource
	private UserDao userDao;

	@Resource
	private VehicleDao vehicleDao;

	@Resource
	private VehicleStatusDao vehicleStatusDao;

	public List<RepairApply> findAll() {
		return repairApplyDao.findAll();
	}

	public void save(String vehicleId, String commentApply, Long userId) throws Exception {
		Vehicle vehicle = vehicleDao.findOne(Long.parseLong(vehicleId));
		if (!RepairStatus.OK.equals(vehicle.getVehicleStatus().getStatus())) {
			throw new Exception("该车正在报修流程中,请勿重复报修!");
		}
		vehicle.setVehicleStatus(vehicleStatusDao.findByStatus(RepairStatus.APPLY_REPAIR));
		vehicle.setTimeRepair(new Date());
		vehicleDao.save(vehicle);
		User user = userDao.findOne(userId);
		RepairApply repairApply = new RepairApply();
		repairApply.setUserApply(user);
		repairApply.setVehicle(vehicle);
		repairApply.setCommentApply(commentApply);
		repairApply.setTimeApply(new Date());
		repairApplyDao.save(repairApply);
	}

	public List<RepairApply> findLike(String userApplyName, String vehicleCode, String userApproveName) {
		List<User> usersApply = userDao.findByUsernameLike("%" + userApplyName + "%");
		List<Vehicle> vehicles = vehicleDao.findByCodeLike("%" + vehicleCode + "%");
		List<Long> userApplyIds = new ArrayList<>();
		List<Long> vehicleIds = new ArrayList<>();
		for (User user : usersApply) {
			userApplyIds.add(user.getId());
		}
		for (Vehicle vehicle : vehicles) {
			vehicleIds.add(vehicle.getId());
		}
		if (StringUtil.isEmpty(userApproveName)) {
			return repairApplyDao.findByUserApplyIdInAndVehicleIdIn(userApplyIds, vehicleIds);
		}
		List<User> usersApprove = userDao.findByUsernameLike("%" + userApproveName + "%");
		List<Long> userApproveIds = new ArrayList<>();
		for (User user : usersApprove) {
			userApproveIds.add(user.getId());
		}
		return repairApplyDao.findByUserApplyIdInAndVehicleIdInAndUserApproveIdIn(userApplyIds, vehicleIds,
				userApproveIds);
	}

	public void remove(List<Long> ids) throws Exception {
		List<RepairApply> list = repairApplyDao.findByIdIn(ids);
		for (RepairApply each : list) {
			if (each.isFlagApprove()) {
				throw new Exception("含有已审核过的选项,不能删除！");
			}
		}
		repairApplyDao.deleteByIdIn(ids);
		for (RepairApply each : list) {
			Vehicle vehicle = each.getVehicle();
			vehicle.setVehicleStatus(vehicleStatusDao.findByStatus(RepairStatus.OK));
			vehicleDao.save(vehicle);
		}
	}

	public void passAndCreateNotice(List<Long> ids, User user) throws Exception {
		List<RepairApply> list = repairApplyDao.findByIdIn(ids);
		for (RepairApply each : list) {
			if (each.isFlagApprove()) {
				continue;
			}
			Vehicle vehicle = each.getVehicle();
			vehicle.setVehicleStatus(vehicleStatusDao.findByStatus(RepairStatus.NOTICE_REPAIR));
			vehicleDao.save(vehicle);
			each.setFlagApprove(true);
			each.setVehicle(vehicle);
			each.setUserApprove(user);
			each.setTimeApprove(new Date());
			repairApplyDao.save(each);
			// 创建维修通知
			RepairNotice repairNotice = new RepairNotice();
			repairNotice.setCommentNotice("车辆" + vehicle.getCode() + "已损坏,需要维修部门处理!");
			repairNotice.setTimeNotice(new Date());
			repairNotice.setUserNotice(user);
			repairNotice.setVehicle(vehicle);
			repairNoticeDao.save(repairNotice);
		}
	}

	public void broken(List<Long> ids, Long userId) throws Exception {
		List<RepairApply> list = repairApplyDao.findByIdIn(ids);
		for (RepairApply each : list) {
			Vehicle vehicle = each.getVehicle();
			if (!RepairStatus.APPLY_REPAIR.equals(vehicle.getVehicleStatus().getStatus())) {
				throw new Exception("不能报废非待修车辆!");
			}
		}
		for (RepairApply each : list) {
			if (each.isFlagApprove()) {
				continue;
			}
			Vehicle vehicle = each.getVehicle();
			vehicle.setVehicleStatus(vehicleStatusDao.findByStatus(RepairStatus.BAD));
			vehicleDao.save(vehicle);
			each.setFlagApprove(true);
			each.setVehicle(vehicle);
			User user = userDao.findOne(userId);
			each.setUserApprove(user);
			each.setTimeApprove(new Date());
			repairApplyDao.save(each);
		}
	}
}
