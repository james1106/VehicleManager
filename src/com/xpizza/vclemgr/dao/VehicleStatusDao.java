package com.xpizza.vclemgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xpizza.vclemgr.domain.VehicleStatus;

/**
 * 用户.数据层
 */
public interface VehicleStatusDao extends JpaRepository<VehicleStatus, Long> {

	VehicleStatus findByStatus(String status);

	@Transactional
	void deleteByIdIn(List<Long> ids);

}
