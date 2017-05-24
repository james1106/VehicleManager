package com.xpizza.vclemgr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.core.lang.StringUtil;
import com.xpizza.vclemgr.dao.EquipmentDao;
import com.xpizza.vclemgr.dao.PurchasePlanDao;
import com.xpizza.vclemgr.dao.UserDao;
import com.xpizza.vclemgr.domain.Equipment;
import com.xpizza.vclemgr.domain.PurchasePlan;
import com.xpizza.vclemgr.domain.User;

@Service
public class PurchasePlanService {

	@Resource
	private PurchasePlanDao purchasePlanDao;

	@Resource
	private UserDao userDao;

	@Resource
	private EquipmentDao equipmentDao;

	public List<PurchasePlan> findAll() {
		return purchasePlanDao.findAll();
	}

	public void save(String equipmentId, String num, String commentApply, Long userId) throws Exception {
		PurchasePlan purchasePlan = new PurchasePlan();
		purchasePlan.setEquipment(equipmentDao.findOne(Long.parseLong(equipmentId.trim())));
		purchasePlan.setUserApply(userDao.findOne(userId));
		purchasePlan.setCommentApply(commentApply);
		purchasePlan.setNum(Long.parseLong(num));
		purchasePlan.setTimeApply(new Date());
		purchasePlanDao.save(purchasePlan);
	}

	public List<PurchasePlan> findLike(String userApplyName, String equipmentName, String userApproveName) {
		List<User> usersApply = userDao.findByUsernameLike("%" + userApplyName + "%");
		List<Equipment> equipments = equipmentDao.findByNameLike("%" + equipmentName + "%");
		List<Long> userApplyIds = new ArrayList<>();
		List<Long> equipmentIds = new ArrayList<>();
		for (User user : usersApply) {
			userApplyIds.add(user.getId());
		}
		for (Equipment equipment : equipments) {
			equipmentIds.add(equipment.getId());
		}
		if (StringUtil.isEmpty(userApproveName)) {
			return purchasePlanDao.findByUserApplyIdInAndEquipmentIdIn(userApplyIds, equipmentIds);
		}
		List<User> usersApprove = userDao.findByUsernameLike("%" + userApproveName + "%");
		List<Long> userApproveIds = new ArrayList<>();
		for (User user : usersApprove) {
			userApproveIds.add(user.getId());
		}
		return purchasePlanDao.findByUserApplyIdInAndEquipmentIdInAndUserApproveIdIn(userApplyIds, equipmentIds,
				userApproveIds);
	}

	public void remove(List<Long> ids) throws Exception {
		List<PurchasePlan> list = purchasePlanDao.findByIdIn(ids);
		for (PurchasePlan each : list) {
			if (each.isFlagApprove()) {
				throw new Exception("含有已审核过的选项,不能删除！");
			}
		}
		purchasePlanDao.deleteByIdIn(ids);
	}

	public void pass(List<Long> ids, User user) throws Exception {
		List<PurchasePlan> list = purchasePlanDao.findByIdIn(ids);
		for (PurchasePlan each : list) {
			each.setUserApprove(user);
			each.setFlagApprove(true);
			each.setTimeApprove(new Date());
			purchasePlanDao.save(each);
		}
	}

	public void back(List<Long> ids, Long userId) throws Exception {
		List<PurchasePlan> list = purchasePlanDao.findByIdIn(ids);
		for (PurchasePlan each : list) {
			if (each.isFlagApprove()) {
				continue;
			}
			each.setUserApprove(userDao.findOne(userId));
			each.setFlagApprove(false);
			each.setTimeApprove(new Date());
			purchasePlanDao.save(each);
		}
	}
}
