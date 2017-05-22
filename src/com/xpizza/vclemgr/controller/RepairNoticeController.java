package com.xpizza.vclemgr.controller;

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
import com.xpizza.vclemgr.domain.RepairNotice;
import com.xpizza.vclemgr.service.RepairNoticeService;

/**
 * 维修流程模块
 */
@Controller
@RequestMapping("repairNotice")
public class RepairNoticeController {

	static final Logger logger = LoggerFactory.getLogger(RepairNoticeController.class);

	@Resource
	private RepairNoticeService repairNoticeService;

	@Json
	@RequestMapping("list.action")
	public List<RepairNotice> applyList(String userNotice, String vehicle, String userRepair,
			HttpServletRequest request) {
		return repairNoticeService.findLike(userNotice, vehicle, userRepair);
	}
	/**
	 * 2.管理助手，干活的，进行审批处理，可以修的话，通知修理部门修理 (APPLY_REPAIR --> NOTICE_REPAIR)<br>
	 * 太严重了报废 (NOTICE_REPAIR --> BROKEN)
	 */

	/** 3.1.修理部门接到修理通知，修理，修理完之后再与使用分队交接(NOTICE_REPAIR) */
	@Json
	@RequestMapping("accept.action")
	public Data accept(String ids, HttpServletRequest request, @Session(Constant.SESSION_USERID) Long userId) {
		Data data = new Data();
		try {
			repairNoticeService.accept(idsLongType(ids), userId);
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

	/** 3.2.使用分队关闭维修单,并给予评价(NOTICE_REPAIR --> CLOSE_REPAIR) */

	/** 4.大领导可以看看自己手下的所有信息，审批一下器材筹措计划等 */

}
