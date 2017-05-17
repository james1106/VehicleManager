package com.xpizza.core.mvc.persistence;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.xpizza.core.lang.exception.NotUniqueException;

/**
 * Dao层(包含基础功能:增删改查),指定JdbcTemplate
 */
public abstract class AbstactBindTableDao extends AbstractDao {

	/** Dao层绑定Table对象 */
	private Table table;

	/** 默认的JdbcTemplate是属于主数据源,可以调用set方法自定义设置 */
	@Resource
	private JdbcTemplate mainJdbcTemplate;

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public JdbcTemplate getMainJdbcTemplate() {
		return mainJdbcTemplate;
	}

	public void setMainJdbcTemplate(JdbcTemplate mainJdbcTemplate) {
		this.mainJdbcTemplate = mainJdbcTemplate;
	}

	public AbstactBindTableDao() {
		super();
		this.table = bindTable();
	}

	/**
	 * 
	 * @Title: bindTable
	 * @Description: 绑定 Table
	 * @return
	 * @return: Table
	 */
	protected abstract Table bindTable();

	/** 单表查询,返回所有结果集 */
	public List<Map<String, Object>> findAll() {
		return findAllWithOrderClause(null);
	}

	/** 按范围查找 */
	public List<Map<String, Object>> findRange(List<?> range, String rangeKey) {
		return super.queryRange(mainJdbcTemplate, table.getName(), range, rangeKey);
	}

	/** 单表查询,返回所有结果集&有排序 */
	public List<Map<String, Object>> findAllWithOrderClause(LinkedHashMap<String, OrderType> orderMap) {
		checkTable();
		return super.queryForList(mainJdbcTemplate, orderMap, table.getName());
	}

	/** 单表查询,按查询条件筛选结果集&有筛选条件 */
	public List<Map<String, Object>> findAllWithWhereClause(Map<String, Object> whereMap) {
		return findAllWithWhereAndOrderClause(whereMap, null);
	}

	/** 单表查询,按查询条件筛选结果集&有筛选条件&有排序 */
	public List<Map<String, Object>> findAllWithWhereAndOrderClause(Map<String, Object> whereMap,
			LinkedHashMap<String, OrderType> orderMap) {
		checkTable();
		return super.queryForList(mainJdbcTemplate, whereMap, orderMap, table.getName());
	}

	/** 单表查询,返回所有结果集行数 */
	public int count() {
		checkTable();
		return super.mapCount(mainJdbcTemplate, table.getName());
	}

	/** 单表查询,按查询条件筛选结果集行数 */
	public int count(Map<String, Object> whereMap) {
		checkTable();
		return super.mapCount(mainJdbcTemplate, whereMap, table.getName());
	}

	/** 单表查询,按查询条件找出唯一结果 */
	public Map<String, Object> findOne(Map<String, Object> whereMap) throws NotUniqueException {
		checkTable();
		return super.queryForMap(mainJdbcTemplate, whereMap, table.getName());
	}

	/** 增加一行数据 */
	public void add(Map<String, Object> valMap) {
		checkTable();
		super.mapInsert(mainJdbcTemplate, valMap, table.getName());
	}

	/** 按条件删除数据 */
	public int delete(Map<String, Object> whereMap) {
		checkTable();
		return super.mapDelete(mainJdbcTemplate, whereMap, table.getName());
	}

	/** 按条件修改数据 */
	public void modify(Map<String, Object> valMap, Map<String, Object> whereMap) {
		checkTable();
		super.mapUpdate(mainJdbcTemplate, valMap, whereMap, table.getName());
	}

	/** 保存一条数据,增加或修改 */
	public Map<String, Object> save(Map<String, Object> valMap, Map<String, Object> whereMap)
			throws NotUniqueException {
		if (isNewRecord(whereMap)) {
			this.add(valMap);
		} else {
			this.modify(valMap, whereMap);
		}
		return this.findOne(whereMap);
	}

	/** 保存一条数据,增加或修改 */
	public Map<String, Object> save(Map<String, Object> addValMap, Map<String, Object> modifyValMap,
			Map<String, Object> whereMap) throws NotUniqueException {
		if (isNewRecord(whereMap)) {
			this.add(addValMap);
		} else {
			this.modify(modifyValMap, whereMap);
		}
		return this.findOne(whereMap);
	}

	/** 保存一条数据,增加或修改(过时) */
	@Deprecated
	public Map<String, Object> save(Map<String, Object> valMap, Map<String, Object> whereMap, String... idKeys)
			throws NotUniqueException {
		Map<String, Object> pkMap = new HashMap<>();
		for (String key : idKeys) {
			pkMap.put(key, whereMap.get(key));
		}
		if (isNewRecord(pkMap)) {
			this.add(valMap);
		} else {
			this.modify(valMap, whereMap);
		}
		return this.findOne(whereMap);
	}

	protected OrderType getOrderType(String sortType) {
		switch (sortType) {
		case "0":
			return OrderType.DESC;
		case "1":
			return OrderType.ASC;
		default:
			return OrderType.DEFAULT;
		}
	}

	/**
	 * 
	 * @Title: isNewRecord
	 * @Description: 是否是新记录(新:insert;旧:update)
	 * @param whereMap
	 * @return
	 * @throws NotUniqueException
	 * @return: boolean
	 */
	private boolean isNewRecord(Map<String, Object> whereMap) throws NotUniqueException {
		return CollectionUtils.isEmpty(findOne(whereMap));
	}

	/**
	 * 
	 * @Title: checkTable
	 * @Description: 检查是否对 SimpleDao 绑定了 Table
	 * @return: void
	 */
	private void checkTable() {
		Assert.notNull(table, "未对Dao绑定表");
	}

	public String getMaxLimitString(String oldStr, int maxLength) {
		if (oldStr.length() > maxLength) {
			return oldStr.substring(0, maxLength);
		}
		return oldStr;
	}

}
