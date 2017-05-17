package com.xpizza.vclemgr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpizza.vclemgr.domain.User;

/**
 * 用户.数据层
 */
public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
