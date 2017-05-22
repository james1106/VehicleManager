package com.xpizza.vclemgr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.xpizza.vclemgr.domain.Vehicle;

/**
 * 车辆.数据层
 */
public interface VehicleDao extends JpaRepository<Vehicle, Long> {

	Vehicle findByCode(String code);

	Vehicle findByDriver(String driver);

	@Transactional
	void deleteByIdIn(List<Long> ids);

	List<Vehicle> findByCodeLikeAndDriverLike(String code, String driver);

	List<Vehicle> findByCodeLike(String vehicleCode);

}
