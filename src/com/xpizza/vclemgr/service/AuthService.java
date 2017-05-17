package com.xpizza.vclemgr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.core.lang.StringUtil;
import com.xpizza.core.security.MD5;
import com.xpizza.core.util.Asserts;
import com.xpizza.vclemgr.dao.UserDao;
import com.xpizza.vclemgr.domain.User;

@Service
public class AuthService {

	@Resource
	private UserDao userDao;

	public User signIn(String username, String password, String remoteAddr) {
		// 校验
		Asserts.isTrue(StringUtil.isNotEmpty(username) && StringUtil.isNotEmpty(password), "用户名或密码不能为空");
		User user = userDao.findByUsername(username);
		Asserts.isNotNull(user, "此用户不存在:" + username);
		String pwdMD5 = MD5.getMD5CodePlus(password);
		Asserts.isTrue(pwdMD5.equals(user.getPassword()), "密码输入错误");
		// 放行
		user.setLastIP(remoteAddr);
		userDao.save(user);
		return user;
	}

}
