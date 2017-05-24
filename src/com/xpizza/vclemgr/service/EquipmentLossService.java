package com.xpizza.vclemgr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.core.util.DateUtil;
import com.xpizza.plugin.echarts.ChartData;
import com.xpizza.vclemgr.dao.EquipmentLossDao;
import com.xpizza.vclemgr.domain.Equipment;
import com.xpizza.vclemgr.domain.EquipmentLoss;

@Service
public class EquipmentLossService {

	@Resource
	private EquipmentLossDao equipmentLossDao;

	public Map<String, Integer> singleLine(String eqId) {

		List<EquipmentLoss> all = equipmentLossDao.findByRquipmentId(Long.parseLong(eqId));
		Map<String, Integer> map = new HashMap<>();
		for (EquipmentLoss each : all) {
			Date timeBroken = each.getTimeBroken();
			String date = DateUtil.dateToString(timeBroken, DateUtil.FORMAT_BAR0);
			if (!map.containsKey(date)) {
				map.put(date, 1);
			} else {
				int count = map.get(date);
				map.put(date, count + 1);
			}
		}
		return map;
	}

	public List<ChartData> drawPie() {
		List<ChartData> pie = new ArrayList<>();
		List<EquipmentLoss> els = equipmentLossDao.findAll();
		Map<Equipment, Integer> map = new HashMap<>();
		for (EquipmentLoss each : els) {
			Equipment equipment = each.getRquipment();
			if (!map.containsKey(equipment)) {
				map.put(equipment, 1);
			} else {
				Integer count = map.get(equipment);
				map.put(equipment, count + 1);
			}
		}
		Iterator<Equipment> it = map.keySet().iterator();
		while (it.hasNext()) {
			Equipment equipment = it.next();
			ChartData chartData = new ChartData();
			chartData.setName(equipment.getName());
			chartData.setValue(map.get(equipment));
			pie.add(chartData);
		}
		return pie;
	}

}
