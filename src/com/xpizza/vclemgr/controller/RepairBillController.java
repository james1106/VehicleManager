package com.xpizza.vclemgr.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xpizza.core.anno.Json;
import com.xpizza.core.anno.Session;
import com.xpizza.core.lang.StringUtil;
import com.xpizza.core.mvc.Data;
import com.xpizza.core.util.Asserts;
import com.xpizza.vclemgr.constant.Constant;
import com.xpizza.vclemgr.domain.RepairBill;
import com.xpizza.vclemgr.service.RepairBillService;
import com.xpizza.vclemgr.service.RepairNoticeService;

/**
 * 维修流程模块
 */
@Controller
@RequestMapping("repairBill")
public class RepairBillController {

	static final Logger logger = LoggerFactory.getLogger(RepairBillController.class);

	@Resource
	private RepairBillService repairBillService;

	@Resource
	private RepairNoticeService repairNoticeService;

	@Json
	@RequestMapping("list.action")
	public List<RepairBill> applyList(String userRepair, String vehicle, 
			HttpServletRequest request) {
		return repairBillService.findLike(userRepair, vehicle);
	}

	@Json
	@RequestMapping("save.action")
	public Data save(String flagRepair, String equipment, String vehicleId, String commentRepair,
			@Session(Constant.SESSION_USERID) Long userId, HttpServletRequest request) {
		Data data = new Data();
		try {
			Asserts.isTrue(StringUtil.isNotEmpty(flagRepair) && StringUtil.isNotEmpty(equipment)
					&& StringUtil.isNotEmpty(vehicleId) && StringUtil.isNotEmpty(commentRepair), "请完善表单");
			repairBillService.save(flagRepair, equipment, vehicleId, commentRepair, userId);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

}
