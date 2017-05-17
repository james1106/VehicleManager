package com.xpizza.vclemgr.constant;

/**
 * 维修状态
 */
public class RepairStatus {

	/** 车辆损坏,无法修理 */
	public static final String BAD = "0";

	/** 车辆完好 */
	public static final String OK = "1";

	/** 申请修理 */
	public static final String APPLY_REPAIR = "2";

	/** 通知修理 */
	public static final String NOTICE_REPAIR = "3";

	/** 关闭修理(修理完成) */
	public static final String CLOSE_REPAIR = "4";

}
