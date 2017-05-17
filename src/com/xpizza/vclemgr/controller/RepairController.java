package com.xpizza.vclemgr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 维修流程模块
 */
@Controller
@RequestMapping("repair")
public class RepairController {

	static final Logger logger = LoggerFactory.getLogger(RepairController.class);

	/**
	 * 1.使用分队提交申请<br>
	 * SET STATUS = APPLY_REPAIR
	 */

	/**
	 * 2.管理助手，干活的，进行审批处理，可以修的话，通知修理部门修理 (APPLY_REPAIR --> NOTICE_REPAIR)<br>
	 * 太严重了报废 (NOTICE_REPAIR --> BROKEN)
	 */

	/** 3.1.修理部门接到修理通知，修理，修理完之后再与使用分队交接(NOTICE_REPAIR) */

	/** 3.2.使用分队关闭维修单,并给予评价(NOTICE_REPAIR --> CLOSE_REPAIR) */

	/** 4.大领导可以看看自己手下的所有信息，审批一下器材筹措计划等 */

}
