package com.xpizza.vclemgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xpizza.vclemgr.domain.Equipment;

/**
 * 用户.数据层
 */
public interface EquipmentDao extends JpaRepository<Equipment, Long> {

	Equipment findByName(String name);

	List<Equipment> findByIdIn(List<Long> ids);

	@Transactional
	void deleteByIdIn(List<Long> ids);

	List<Equipment> findByNameLike(String string);

}
