package com.xpizza.vclemgr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xpizza.core.mvc.Data;

/**
 * 权限模块(登录/注册)
 */
@Controller
@RequestMapping("auth")
public class AuthController {

	static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@RequestMapping({ "sign-in.page", "sign-up.page", "reset-password.page" })
	public void showPages() {
	}

	@ResponseBody
	@RequestMapping(value = "download.action", produces = "application/json;charset=utf-8")
	public Data download() {
		Data data = new Data();
		return data;
	}

}
