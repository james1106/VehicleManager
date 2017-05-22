package com.xpizza.vclemgr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xpizza.vclemgr.dao.RoleDao;
import com.xpizza.vclemgr.domain.Role;

@Service
public class RoleService {

	@Resource
	private RoleDao roleDao;

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public void save(String name, String comment) {
		Role role = new Role();
		role.setName(name);
		role.setComment(comment);
		roleDao.save(role);
	}

	public void remove(List<Long> ids) {
		roleDao.deleteByIdIn(ids);
	}

}
