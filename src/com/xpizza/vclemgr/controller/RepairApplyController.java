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
import com.xpizza.core.anno.Session;
import com.xpizza.core.mvc.Data;
import com.xpizza.vclemgr.constant.Constant;
import com.xpizza.vclemgr.domain.RepairApply;
import com.xpizza.vclemgr.domain.User;
import com.xpizza.vclemgr.service.RepairApplyService;

/**
 * 维修流程模块
 */
@Controller
@RequestMapping("repairApply")
public class RepairApplyController {

	static final Logger logger = LoggerFactory.getLogger(RepairApplyController.class);

	@Resource
	private RepairApplyService repairApplyService;

	/**
	 * 1.使用分队提交申请<br>
	 * SET STATUS = APPLY_REPAIR
	 */
	@Json
	@RequestMapping("list.action")
	public List<RepairApply> applyList(String userApply, String vehicle, String userApprove,
			HttpServletRequest request) {
		return repairApplyService.findLike(userApply, vehicle, userApprove);
	}

	@Json
	@RequestMapping("doApply.action")
	public Data doApply(String vehicleId, String commentApply, @Session(Constant.SESSION_USERID) Long userId)
			throws IOException {
		Data data = new Data();
		try {
			repairApplyService.save(vehicleId.trim(), commentApply, userId);
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
			repairApplyService.remove(idsLongType);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

	@Json
	@RequestMapping("pass.action")
	public Data pass(String ids, HttpServletRequest request, @Session(Constant.SESSION_USER) User user) {
		Data data = new Data();
		try {
			repairApplyService.passAndCreateNotice(idsLongType(ids), user);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

	@Json
	@RequestMapping("broken.action")
	public Data broken(String ids, HttpServletRequest request, @Session(Constant.SESSION_USERID) Long userId) {
		Data data = new Data();
		try {
			repairApplyService.broken(idsLongType(ids), userId);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

	private List<Long> idsLongType(String ids) {
		List<Long> idsLongType = new ArrayList<>();
		String[] idStrs = ids.split(",");
		for (String idStr : idStrs) {
			idsLongType.add(Long.parseLong(idStr));
		}
		return idsLongType;
	}

}
