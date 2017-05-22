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
import com.xpizza.core.lang.StringUtil;
import com.xpizza.core.mvc.Data;
import com.xpizza.core.util.Asserts;
import com.xpizza.vclemgr.constant.Constant;
import com.xpizza.vclemgr.domain.User;
import com.xpizza.vclemgr.service.UserService;

/**
 * 用户模块
 */
@Controller
@RequestMapping("user")
public class UserController {

	static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;

	@Json
	@RequestMapping("list.action")
	public List<User> list(String username, String role, String status, HttpServletRequest request) {
		List<User> users = userService.findAll();
		return users;
	}

	@Json
	@RequestMapping("myself.action")
	public Data myself(@Session(Constant.SESSION_USER) User user) {
		Data data = new Data();
		try {
			data.add("user", user);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

	@Json
	@RequestMapping("save.action")
	public Data save(String username, String password, String role, HttpServletRequest request) {
		Data data = new Data();
		try {
			Asserts.isTrue(
					StringUtil.isNotEmpty(username) && StringUtil.isNotEmpty(password) && StringUtil.isNotEmpty(role),
					"请完善表单");
			userService.save(username, password, Long.parseLong(role));
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
			userService.remove(idsLongType);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

}
