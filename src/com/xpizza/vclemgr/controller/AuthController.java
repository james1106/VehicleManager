package com.xpizza.vclemgr.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xpizza.core.anno.Json;
import com.xpizza.core.anno.Session;
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

	@RequestMapping(Constant.PAGE_SIGN_IN)
	public ModelAndView signIn(ModelAndView mav, @Session(Constant.SESSION_USER) User user) {
		if (user != null) {
			mav.setViewName("console/home");
			return mav;
		}
		return mav;
	}

	@RequestMapping({ Constant.PAGE_SIGN_UP, Constant.PAGE_RESET_PASSWORD })
	public void showPages() {
	}

	@RequestMapping("signOut.do")
	public ModelAndView signOut(ModelAndView mav, HttpServletRequest request) {
		request.getSession().invalidate();
		mav.setViewName("auth/sign-in");
		return mav;
	}

	@Json
	@RequestMapping("signIn.action")
	public Data signIn(String username, String password, HttpServletRequest request) {
		Data data = new Data();
		try {
			User signedUser = authService.signIn(username, password, NetUtil.getRemoteAddr(request));
			// 登录之前把User的关键信息存入session中
			HttpSession session = request.getSession();
			session.setAttribute(Constant.SESSION_USER, signedUser);
			session.setAttribute(Constant.SESSION_USERNAME, signedUser.getUsername());
			session.setAttribute(Constant.SESSION_USERID, signedUser.getId());
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

	@Json
	@RequestMapping("rePwd.action")
	public Data rePwd(String oldPwd, String newPwd, String newPwd2, HttpServletRequest request,
			@Session(Constant.SESSION_USER) User user) {
		Data data = new Data();
		try {
			authService.rePwd(oldPwd, newPwd, newPwd2, user);
			request.getSession().invalidate();
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

	@Json
	@RequestMapping("getRole.action")
	public Data getRole(@Session(Constant.SESSION_USER) User user) {
		Data data = new Data();
		try {
			data.add("role", user.getRole());
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

}
