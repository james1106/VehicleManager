package com.xpizza.vclemgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xpizza.vclemgr.domain.EquipmentLoss;

/**
 * 用户.数据层
 */
public interface EquipmentLossDao extends JpaRepository<EquipmentLoss, Long> {

	List<EquipmentLoss> findByIdIn(List<Long> ids);

	List<EquipmentLoss> findByRquipmentId(Long eqId);

	@Transactional
	void deleteByIdIn(List<Long> ids);

}
