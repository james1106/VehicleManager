package com.xpizza.vclemgr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xpizza.core.anno.Json;
import com.xpizza.core.lang.StringUtil;
import com.xpizza.core.mvc.Data;
import com.xpizza.core.util.Asserts;
import com.xpizza.vclemgr.domain.Equipment;
import com.xpizza.vclemgr.service.EquipmentService;

/**
 * 车辆附件模块
 */
@Controller
@RequestMapping("equipment")
public class EquipmentController {

	static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

	@Resource
	private EquipmentService equipmentService;

	@Json
	@RequestMapping("list.action")
	public List<Equipment> list() throws IOException {
		List<Equipment> list = equipmentService.findAll();
		return list;
	}

	@Json
	@RequestMapping("save.action")
	public Data save(String name, HttpServletRequest request) {
		Data data = new Data();
		try {
			Asserts.isTrue(StringUtil.isNotEmpty(name), "请完善表单");
			equipmentService.save(name);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

	@Json
	@RequestMapping("remove.action")
	public Data remove(String ids, HttpServletRequest request) {
		Data data = new Data();
		try {
			List<Long> idsLongType = new ArrayList<>();
			String[] idStrs = ids.split(",");
			for (String idStr : idStrs) {
				idsLongType.add(Long.parseLong(idStr));
			}
			equipmentService.remove(idsLongType);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

}
