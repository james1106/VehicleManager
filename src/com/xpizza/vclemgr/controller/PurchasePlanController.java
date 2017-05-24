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
import com.xpizza.vclemgr.domain.PurchasePlan;
import com.xpizza.vclemgr.domain.User;
import com.xpizza.vclemgr.service.PurchasePlanService;

/**
 * 维修流程模块
 */
@Controller
@RequestMapping("purchasePlan")
public class PurchasePlanController {

	static final Logger logger = LoggerFactory.getLogger(PurchasePlanController.class);

	@Resource
	private PurchasePlanService purchasePlanService;

	@Json
	@RequestMapping("list.action")
	public List<PurchasePlan> applyList(String userApply, String equipment, String userApprove,
			HttpServletRequest request) {
		return purchasePlanService.findLike(userApply, equipment, userApprove);
	}

	@Json
	@RequestMapping("save.action")
	public Data doApply(String eqId, String num, String reason, @Session(Constant.SESSION_USERID) Long userId)
			throws IOException {
		Data data = new Data();
		try {
			purchasePlanService.save(eqId, num, reason, userId);
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
			purchasePlanService.remove(idsLongType);
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
			purchasePlanService.pass(idsLongType(ids), user);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

	@Json
	@RequestMapping("back.action")
	public Data broken(String ids, HttpServletRequest request, @Session(Constant.SESSION_USERID) Long userId) {
		Data data = new Data();
		try {
			purchasePlanService.back(idsLongType(ids), userId);
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
