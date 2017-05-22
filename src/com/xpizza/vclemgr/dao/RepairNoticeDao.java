package com.xpizza.vclemgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xpizza.vclemgr.domain.RepairNotice;

/**
 * 维修申请.Dao
 */
public interface RepairNoticeDao extends JpaRepository<RepairNotice, Long> {

	RepairNotice findByUserNotice(String userNotice);

	@Transactional
	void deleteByIdIn(List<Long> ids);

	List<RepairNotice> findByIdIn(List<Long> ids);

	List<RepairNotice> findByUserNoticeIdInAndVehicleIdIn(List<Long> userNoticeIds, List<Long> vehicleIds);

	List<RepairNotice> findByUserNoticeIdInAndVehicleIdInAndUserRepairIdIn(List<Long> userNoticeIds,
			List<Long> vehicleIds, List<Long> userRepairIds);

}
