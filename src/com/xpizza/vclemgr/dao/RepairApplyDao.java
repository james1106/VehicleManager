package com.xpizza.vclemgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xpizza.vclemgr.domain.RepairApply;

/**
 * 维修申请.Dao
 */
public interface RepairApplyDao extends JpaRepository<RepairApply, Long> {

	RepairApply findByUserApply(String userApply);

	@Transactional
	void deleteByIdIn(List<Long> ids);

	List<RepairApply> findByIdIn(List<Long> ids);

	List<RepairApply> findByUserApplyIdInAndVehicleIdInAndUserApproveIdIn(List<Long> userApplyIds,
			List<Long> vehicleIds, List<Long> userApproveIds);

	List<RepairApply> findByUserApplyIdInAndVehicleIdIn(List<Long> userApplyIds, List<Long> vehicleIds);

}
