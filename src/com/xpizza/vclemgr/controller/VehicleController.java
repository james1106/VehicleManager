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
import com.xpizza.vclemgr.domain.Vehicle;
import com.xpizza.vclemgr.service.VehicleService;

/**
 * 车辆模块
 */
@Controller
@RequestMapping("vehicle")
public class VehicleController {

	static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

	@Resource
	private VehicleService vehicleService;

	@Json
	@RequestMapping("list.action")
	public List<Vehicle> list(String code, String driver, HttpServletRequest request) throws IOException {
		List<Vehicle> list = vehicleService.findByContition(code, driver);
		return list;
	}

	@Json
	@RequestMapping("save.action")
	public Data save(String code, String mileage, String attachments, String driver, HttpServletRequest request) {
		Data data = new Data();
		try {
			Asserts.isTrue(StringUtil.isNotEmpty(code) && StringUtil.isNotEmpty(mileage)
					&& StringUtil.isNotEmpty(attachments) && StringUtil.isNotEmpty(driver), "请完善表单");
			vehicleService.save(code, mileage, attachments, driver);
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
			vehicleService.remove(idsLongType);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

}
