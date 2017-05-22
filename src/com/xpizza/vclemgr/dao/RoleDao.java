package com.xpizza.vclemgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xpizza.vclemgr.domain.Role;

/**
 * 角色.数据层
 */
public interface RoleDao extends JpaRepository<Role, Long> {

	Role findByName(String name);

	@Transactional
	void deleteByIdIn(List<Long> ids);

}
