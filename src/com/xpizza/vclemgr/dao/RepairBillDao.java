package com.xpizza.vclemgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xpizza.vclemgr.domain.RepairBill;

/**
 * 维修申请.Dao
 */
public interface RepairBillDao extends JpaRepository<RepairBill, Long> {

	RepairBill findByUserRepair(String userRepair);

	@Transactional
	void deleteByIdIn(List<Long> ids);

	List<RepairBill> findByIdIn(List<Long> ids);

	List<RepairBill> findByUserRepairIdInAndVehicleIdIn(List<Long> userRepairs, List<Long> vehicleIds);

}
