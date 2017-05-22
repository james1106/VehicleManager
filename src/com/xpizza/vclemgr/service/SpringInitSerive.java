package com.xpizza.vclemgr.service;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xpizza.core.lang.StringUtil;
import com.xpizza.core.security.MD5;
import com.xpizza.vclemgr.constant.Constant;
import com.xpizza.vclemgr.constant.RepairStatus;
import com.xpizza.vclemgr.dao.EquipmentDao;
import com.xpizza.vclemgr.dao.RoleDao;
import com.xpizza.vclemgr.dao.UserDao;
import com.xpizza.vclemgr.dao.VehicleDao;
import com.xpizza.vclemgr.dao.VehicleStatusDao;
import com.xpizza.vclemgr.domain.Equipment;
import com.xpizza.vclemgr.domain.Role;
import com.xpizza.vclemgr.domain.User;
import com.xpizza.vclemgr.domain.Vehicle;
import com.xpizza.vclemgr.domain.VehicleStatus;

/**
 * 
 * @ClassName: MemorySerive
 * @Description: 启动项目的时候本地化配置
 * @author: Xpizza
 * @date: Jan 4, 2017 10:19:17 AM
 */
@Service
public class SpringInitSerive {

	static final Logger logger = LoggerFactory.getLogger(SpringInitSerive.class);

	@Resource
	private RoleDao roleDao;

	@Resource
	private UserDao userDao;

	@Resource
	private VehicleStatusDao vehicleStatusDao;

	@Resource
	private EquipmentDao equipmentDao;

	@Resource
	private VehicleDao vehicleDao;

	/**
	 * 
	 * @Title: afterSpringStartup
	 * @Description: Spring启动完成之后干的事
	 * @return: void
	 */
	@PostConstruct
	public void afterSpringStartup() {
		String roleName1 = "使用分队";
		String roleName2 = "管理助手";
		String roleName3 = "主管领导";
		String roleName4 = "维修部门";
		// 角色数据
		dataForRole(roleName1, roleName2, roleName3, roleName4);
		// 用户数据
		dataForUser(roleName1, roleName2, roleName3, roleName4);
		// 车辆状态数据
		dataForVehicleStatus();
		// 器材数据
		dataForEquipment();
		// 车辆数据
		dataForVehicle();
	}

	private void dataForVehicle() {
		addVehicle("A1", "令狐冲");
		addVehicle("A2", "独孤求败");
	}

	private void addVehicle(String code, String driver) {
		Vehicle exsitOne = vehicleDao.findByCode(code);
		if (exsitOne != null) {
			logger.warn("已存在此车辆:" + exsitOne);
			return;
		}
		Vehicle newOne = new Vehicle();
		newOne.setCode(code);
		newOne.setMileage("300");
		newOne.setAttachments("附件" + StringUtil.get32UUID().substring(6, 12));
		newOne.setDriver(driver);
		newOne.setVehicleStatus(vehicleStatusDao.findByStatus(RepairStatus.OK));
		newOne.setTimeCreate(new Date());
		vehicleDao.save(newOne);
		logger.info("创建新车辆:" + newOne);
	}

	private void dataForUser(String roleName1, String roleName2, String roleName3, String roleName4) {
		Role role1 = roleDao.findByName(roleName1);
		Role role2 = roleDao.findByName(roleName2);
		Role role3 = roleDao.findByName(roleName3);
		Role role4 = roleDao.findByName(roleName4);
		dataForUser(role1, "张三");
		dataForUser(role2, "李四");
		dataForUser(role3, "王五");
		dataForUser(role4, "赵六");
		dataForUser(role1, "张大");
	}

	private void dataForUser(Role role, String username) {
		User exsitOne = userDao.findByUsername(username);
		if (exsitOne != null) {
			logger.warn("已存在此用户:" + exsitOne);
			return;
		}
		User newOne = new User();
		newOne.setUsername(username);
		newOne.setPassword(MD5.getMD5CodePlus("123"));
		newOne.setRole(role);
		newOne.setStatus(Constant.USER_EFFECTIVE);
		newOne.setTimeCreate(new Date());
		userDao.save(newOne);
		logger.info("创建新用户:" + newOne);
	}

	private void dataForRole(String role1, String role2, String role3, String role4) {
		saveRole(role1, "提交申请/查看维修详情/维修后评价");
		saveRole(role2, "超级管理员");
		saveRole(role3, "查看所有信息管理，审批器材筹措计划");
		saveRole(role4, "接收维修通知,修理完交接");
	}

	private void saveRole(String name, String comment) {
		Role exsitOne = roleDao.findByName(name);
		if (exsitOne != null) {
			logger.warn("已存在此角色:" + exsitOne);
			return;
		}
		Role newOne = new Role();
		newOne.setName(name);
		newOne.setComment(comment);
		logger.info("创建新角色:" + newOne);
		roleDao.save(newOne);
	}

	private void dataForEquipment() {
		saveEquipment("轮胎");
		saveEquipment("齿轮");
		saveEquipment("发动机");
		saveEquipment("前档");
		saveEquipment("后档");
		saveEquipment("刹车");
		saveEquipment("方向盘");
		saveEquipment("操作杆");
		saveEquipment("玻璃");
	}

	private void saveEquipment(String name) {
		Equipment exsitOne = equipmentDao.findByName(name);
		if (exsitOne != null) {
			logger.warn("已存在此设备:" + exsitOne);
			return;
		}
		Equipment newOne = new Equipment();
		newOne.setName(name);
		logger.info("创建新设备:" + newOne);
		equipmentDao.save(newOne);
	}

	private void dataForVehicleStatus() {
		if (vehicleStatusDao.count() >= 4) {
			logger.info("车辆状态已维护");
			return;
		}
		logger.warn("车辆状态为空,执行初始化动作");
		// 报废
		saveVehicleStatus(RepairStatus.BAD, "报废");
		// 正常
		saveVehicleStatus(RepairStatus.OK, "正常");
		// 申请修理
		saveVehicleStatus(RepairStatus.APPLY_REPAIR, "申请报修");
		// 通知报修
		saveVehicleStatus(RepairStatus.NOTICE_REPAIR, "通知报修");
		// 关闭修理 --> ok
	}

	private void saveVehicleStatus(String status, String comment) {
		VehicleStatus exsitOne = vehicleStatusDao.findByStatus(status);
		if (exsitOne != null) {
			logger.warn("已存在此维修状态:" + exsitOne);
		}
		VehicleStatus newOne = new VehicleStatus();
		newOne.setStatus(status);
		newOne.setComment(comment);
		logger.info("创建新维修状态:" + newOne);
		vehicleStatusDao.save(newOne);
	}

}
