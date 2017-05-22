package com.xpizza.vclemgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xpizza.core.mvc.AbstractIdDomain;

/**
 * 用户
 */
@Entity
@Table(name = "sys02_user")
public class User extends AbstractIdDomain implements java.io.Serializable {

	private static final long serialVersionUID = 5758558888038261836L;

	/** 用户名 */
	@Column(name = "username", nullable = false, length = 32)
	private String username;

	/** 密码MD5 */
	@Column(name = "password", nullable = false, length = 32)
	private String password;

	/** 所属角色 */
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;

	/** 状态 0：冻结 1:启用 */
	@Column(name = "status", nullable = false)
	private int status;

	/** 最后一次登录IP */
	@Column(name = "lastIP", length = 32)
	private String lastIP;

	/** 创建时间 */
	@Column(name = "timeCreate", nullable = false)
	private Date timeCreate;

	/** 访问时间 */
	@Column(name = "timeView")
	private Date timeView;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLastIP() {
		return lastIP;
	}

	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}

	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Date getTimeView() {
		return timeView;
	}

	public void setTimeView(Date timeView) {
		this.timeView = timeView;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", role=" + role + ", status=" + status
				+ ", lastIP=" + lastIP + ", timeCreate=" + timeCreate + ", timeView=" + timeView + "]";
	}

}
