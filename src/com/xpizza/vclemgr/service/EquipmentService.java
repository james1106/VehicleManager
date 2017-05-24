package com.xpizza.vclemgr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.vclemgr.dao.EquipmentDao;
import com.xpizza.vclemgr.domain.Equipment;

@Service
public class EquipmentService {

	@Resource
	private EquipmentDao equipmentDao;

	public List<Equipment> findAll() {
		return equipmentDao.findAll();
	}

	public void save(String name) {
		Equipment equipment = new Equipment();
		equipment.setName(name);
		equipment.setStock(0L);
		equipmentDao.save(equipment);
	}

	public void remove(List<Long> ids) {
		equipmentDao.deleteByIdIn(ids);
	}

	public void updateNum(String eqId, String num) {
		Equipment equipment = equipmentDao.findOne(Long.parseLong(eqId));
		Long stock = equipment.getStock();
		stock += Long.parseLong(num.trim());
		equipment.setStock(stock);
		equipmentDao.save(equipment);
	}

}
