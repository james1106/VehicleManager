package com.xpizza.vclemgr.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xpizza.core.anno.Json;
import com.xpizza.core.mvc.Data;
import com.xpizza.core.util.NetUtil;
import com.xpizza.vclemgr.constant.Constant;
import com.xpizza.vclemgr.domain.User;
import com.xpizza.vclemgr.service.AuthService;

/**
 * 权限模块(登录/注册)
 */
@Controller
@RequestMapping("auth")
public class AuthController {

	static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Resource
	private AuthService authService;

	@RequestMapping({ Constant.PAGE_SIGN_IN, Constant.PAGE_SIGN_UP, Constant.PAGE_RESET_PASSWORD })
	public void showPages() {
	}

	/*
	@ResponseBody
	@RequestMapping(value = "signIn.action", produces = "application/json;charset=utf-8")
	*/
	@Json
	@RequestMapping("signIn.action")
	public Data signIn(String username, String password, HttpServletRequest request) {
		Data data = new Data();
		try {
			User signedUser = authService.signIn(username, password, NetUtil.getRemoteAddr(request));
			// 登录之前把User的关键信息存入session中
			HttpSession session = request.getSession();
			session.setAttribute(Constant.SESSION_USERNAME, signedUser.getUsername());
			session.setAttribute(Constant.SESSION_USERID, signedUser.getId());
		} catch (Throwable thr) {
			logger.error(thr.getMessage(), thr);
			data.setError(thr.getMessage());
		}
		return data;
	}

}
