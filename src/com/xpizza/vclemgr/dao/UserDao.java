package com.xpizza.vclemgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xpizza.vclemgr.domain.User;

/**
 * 用户.数据层
 */
public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);

	@Transactional
	void deleteByIdIn(List<Long> ids);

	List<User> findByUsernameLike(String username);

}
