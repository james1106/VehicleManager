package com.xpizza.vclemgr.constant;

/**
 * 恒量
 */
public class Constant {

	/** 配置文件 */
	public static final String[] PROPERTIES_FILENAMES = { "global.properties" };

	/* 特殊页面,不做拦截 */
	/** 登录界面 */
	public static final String PAGE_SIGN_IN = "sign-in.page";
	/** 注册界面 */
	public static final String PAGE_SIGN_UP = "sign-up.page";
	/** 重置密码界面 */
	public static final String PAGE_RESET_PASSWORD = "reset-password.page";

	/* SESEION KEY BEGIN */
	public static final String SESSION_USERNAME = "SESSION_USERNAME";

	public static final String SESSION_USERID = "SESSION_USERID";
	/* SESEION KEY END */

	public static final String[] PAGE_ACCOUNT = null;

	/** 用户有效 */
	public static final int USER_EFFECTIVE = 1;

}
