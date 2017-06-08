package com.xpizza.vclemgr.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.core.lang.StringUtil;
import com.xpizza.core.security.MD5;
import com.xpizza.core.util.Asserts;
import com.xpizza.vclemgr.constant.Constant;
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
		Asserts.isNotNull(user, "用户不存在[" + username + "]");
		Asserts.isTrue(Constant.USER_EFFECTIVE == user.getStatus(), "用户已冻结[" + username + "]");
		String pwdMD5 = MD5.getMD5CodePlus(password);
		Asserts.isTrue(pwdMD5.equals(user.getPassword()), "密码输入错误");
		// 放行
		user.setLastIP(remoteAddr);
		user.setTimeView(new Date());
		userDao.save(user);
		return user;
	}

	public void rePwd(String oldPwd, String newPwd, String newPwd2, User user) {
		Asserts.isTrue(StringUtil.isNotEmpty(oldPwd) && StringUtil.isNotEmpty(newPwd) && StringUtil.isNotEmpty(newPwd2),
				"请填写完密码重置表单");
		Asserts.isTrue(newPwd.equals(newPwd2), "新密码两次输入不一样");
		Asserts.isTrue(!oldPwd.equals(newPwd), "旧密码与新密码不能一致");
		String oldPwdMD5 = MD5.getMD5CodePlus(oldPwd);
		String password = user.getPassword();
		Asserts.isTrue(oldPwdMD5.equals(password), "旧密码错误");
		user.setPassword(MD5.getMD5CodePlus(newPwd));
		userDao.save(user);
	}

}
