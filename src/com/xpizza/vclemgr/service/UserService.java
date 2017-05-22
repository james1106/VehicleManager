package com.xpizza.vclemgr.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.core.security.MD5;
import com.xpizza.vclemgr.constant.Constant;
import com.xpizza.vclemgr.dao.RoleDao;
import com.xpizza.vclemgr.dao.UserDao;
import com.xpizza.vclemgr.domain.User;

@Service
public class UserService {

	@Resource
	private UserDao userDao;

	@Resource
	private RoleDao roleDao;

	public List<User> findAll() {
		return userDao.findAll();
	}

	public void save(String username, String password, Long roleId) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(MD5.getMD5CodePlus(password));
		user.setRole(roleDao.findOne(roleId));
		user.setStatus(Constant.USER_EFFECTIVE);
		user.setTimeCreate(new Date());
		userDao.save(user);
	}

	public void remove(List<Long> ids) {
		userDao.deleteByIdIn(ids);
	}

}
