package com.xpizza.vclemgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xpizza.vclemgr.domain.PurchasePlan;

/**
 * 购买计划.Dao
 */
public interface PurchasePlanDao extends JpaRepository<PurchasePlan, Long> {

	PurchasePlan findByUserApply(String userApply);

	@Transactional
	void deleteByIdIn(List<Long> ids);

	List<PurchasePlan> findByIdIn(List<Long> ids);

	List<PurchasePlan> findByUserApplyIdInAndEquipmentIdIn(List<Long> userApplyIds, List<Long> equipmentIds);

	List<PurchasePlan> findByUserApplyIdInAndEquipmentIdInAndUserApproveIdIn(List<Long> userApplyIds,
			List<Long> equipmentIds, List<Long> userApproveIds);

}
