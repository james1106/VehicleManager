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
import com.xpizza.core.lang.StringUtil;
import com.xpizza.core.mvc.Data;
import com.xpizza.core.util.Asserts;
import com.xpizza.vclemgr.domain.Role;
import com.xpizza.vclemgr.service.RoleService;

/**
 * 角色模块
 */
@Controller
@RequestMapping("role")
public class RoleController {

	static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Resource
	private RoleService roleService;

	@Json
	@RequestMapping("list.action")
	public List<Role> list(String username, String role, String status, HttpServletRequest request) throws IOException {
		List<Role> users = roleService.findAll();
		return users;
	}

	@Json
	@RequestMapping("save.action")
	public Data save(String name, String comment, HttpServletRequest request) {
		Data data = new Data();
		try {
			Asserts.isTrue(StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(comment), "请完善表单");
			roleService.save(name, comment);
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
			roleService.remove(idsLongType);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

}
