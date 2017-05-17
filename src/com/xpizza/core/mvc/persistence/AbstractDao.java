package com.xpizza.core.mvc.persistence;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.xpizza.core.lang.exception.NotUniqueException;

/**
 * 
 * @ClassName: AbstractDao
 * @Description: Abstract Dao(using JdbcTemplate)
 */
public abstract class AbstractDao {

	static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

	/**
	 * 
	 * @Title: queryRange
	 * @Description: 按范围查找
	 * @param jdbcTemplate
	 * @param tableName
	 * @param range
	 *            范围
	 * @param rangeKey
	 *            范围Key
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	protected List<Map<String, Object>> queryRange(JdbcTemplate jdbcTemplate, String tableName, List<?> range,
			String rangeKey) {
		if (CollectionUtils.isEmpty(range)) {
			return null;
		}
		StringBuffer selectSql = new StringBuffer();
		selectSql.append("SELECT * FROM ").append(tableName).append(" WHERE ").append(rangeKey).append(" IN(");
		selectSql.append("?");
		for (int index = 1; index < range.size(); index++) {
			selectSql.append(", ?");
		}
		selectSql.append(")");
		return toLowerKey(jdbcTemplate.queryForList(selectSql.toString(), range.toArray()));
	}

	/**
	 * Query for list
	 */
	protected List<Map<String, Object>> queryForList(JdbcTemplate jdbcTemplate,
			LinkedHashMap<String, OrderType> orderMap, String tableName) {
		StringBuffer selectSql = new StringBuffer();
		selectSql.append("SELECT * FROM ").append(tableName);
		appendOrderClause(selectSql, orderMap);
		debug(selectSql.toString(), null);
		return toLowerKey(jdbcTemplate.queryForList(selectSql.toString()));
	}

	/**
	 * Query for list with where clause
	 */
	protected List<Map<String, Object>> queryForList(JdbcTemplate jdbcTemplate, Map<String, Object> whereMap,
			LinkedHashMap<String, OrderType> orderMap, String tableName) {
		StringBuffer selectSql = new StringBuffer();
		selectSql.append("SELECT * FROM ").append(tableName);
		Object[] args = appendWhereClause(selectSql, whereMap);
		appendOrderClause(selectSql, orderMap);
		debug(selectSql.toString(), args);
		return toLowerKey(jdbcTemplate.queryForList(selectSql.toString(), args));
	}

	/**
	 * Count records
	 */
	protected int mapCount(JdbcTemplate jdbcTemplate, String tableName) {
		String countSql = "SELECT COUNT(1) FROM " + tableName;
		debug(countSql, null);
		return jdbcTemplate.queryForObject(countSql, Integer.class);
	}

	/**
	 * Count records with where clause
	 */
	protected int mapCount(JdbcTemplate jdbcTemplate, Map<String, Object> whereMap, String tableName) {
		StringBuffer countSql = new StringBuffer();
		countSql.append("SELECT COUNT(1) FROM ").append(tableName);
		Object[] args = appendWhereClause(countSql, whereMap);
		debug(countSql.toString(), args);
		return jdbcTemplate.queryForObject(countSql.toString(), args, Integer.class);
	}

	/**
	 * Query for map
	 */
	protected Map<String, Object> queryForMap(JdbcTemplate jdbcTemplate, Map<String, Object> whereMap, String tableName)
			throws NotUniqueException {
		List<Map<String, Object>> list = this.queryForList(jdbcTemplate, whereMap, null, tableName);
		int size = list.size();
		switch (size) {
		case 0:
			return null;
		case 1:
			return list.get(0);
		default:
			logger.error("queryForMap查出超过一行的数据:" + size);
			throw new NotUniqueException();
		}
	}

	/**
	 * Insert with map
	 */
	protected int mapInsert(JdbcTemplate jdbcTemplate, Map<String, Object> valMap, String tableName) {
		Assert.notEmpty(valMap, "执行AbstractDao.mapInsert时valMap不能为空");
		StringBuffer insertSql = new StringBuffer("INSERT INTO ").append(tableName).append(" (");
		StringBuffer placeholders = new StringBuffer("?");// 形参:占位符String,以","分隔
		Object[] args = new Object[valMap.size()];

		Iterator<String> it = valMap.keySet().iterator();
		String firstField = it.next();
		insertSql.append(firstField);
		args[0] = valMap.get(firstField);

		int argsIndex = 1;
		while (it.hasNext()) {
			String field = it.next();
			insertSql.append(",").append(field);
			placeholders.append(",?");
			args[argsIndex] = valMap.get(field);
			argsIndex++;
		}
		insertSql.append(") values (");
		insertSql.append(placeholders).append(")");
		debug(insertSql.toString(), args);
		return jdbcTemplate.update(insertSql.toString(), args);
	}

	/**
	 * Update with map
	 */
	protected int mapUpdate(JdbcTemplate jdbcTemplate, Map<String, Object> valMap, Map<String, Object> whereMap,
			String tableName) {
		Assert.notEmpty(valMap, "执行AbstractDao.mapUpdate时valMap不能为空");
		StringBuffer updateSql = new StringBuffer("UPDATE ").append(tableName).append(" SET ");
		Object[] args = new Object[valMap.size() + whereMap.size()];

		Iterator<String> it = valMap.keySet().iterator();
		String firstField = it.next();
		updateSql.append(firstField).append(" = ?");
		args[0] = valMap.get(firstField);

		int argsIndex = 1;
		while (it.hasNext()) {
			String field = it.next();
			updateSql.append(",").append(field).append(" = ?");
			args[argsIndex] = valMap.get(field);
			argsIndex++;
		}

		Object[] whereArgs = appendWhereClause(updateSql, whereMap);

		for (Object arg : whereArgs) {
			args[argsIndex] = arg;
			argsIndex++;
		}

		debug(updateSql.toString(), args);
		return jdbcTemplate.update(updateSql.toString(), args);
	}

	/**
	 * Delete with map
	 */
	protected int mapDelete(JdbcTemplate jdbcTemplate, Map<String, Object> whereMap, String tableName) {
		Assert.notEmpty(whereMap, "危险操作,禁止全表删除");
		StringBuffer deleteSql = new StringBuffer("DELETE FROM ").append(tableName);
		Object[] args = appendWhereClause(deleteSql, whereMap);
		debug(deleteSql.toString(), args);
		return jdbcTemplate.update(deleteSql.toString(), args);
	}

	// 2016-01-11 xdzhu 将SQL查询出来的colName变成小写（默认全是大写）
	protected List<Map<String, Object>> toLowerKey(List<Map<String, Object>> dataList) {
		List<Map<String, Object>> retList = new LinkedList<Map<String, Object>>();
		for (Map<String, Object> rowMap : dataList) {
			retList.add(toLowerKey(rowMap));
		}
		return (retList);
	}

	// 2016-01-11 xdzhu 将SQL查询出来的colName变成小写（默认全是大写）
	protected Map<String, Object> toLowerKey(Map<String, Object> dataMap) {
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		for (Iterator<String> keyIt = dataMap.keySet().iterator(); keyIt.hasNext();) {
			String key = keyIt.next();
			Object value = dataMap.get(key);
			if (value == null) {
				newMap.put(key.toLowerCase(), "");
			} else {
				newMap.put(key.toLowerCase(), value);
			}
		}
		return (newMap);
	}

	/*
	 * protected String toJson(List<Map<String, Object>> list) { StringBuffer sb
	 * = new StringBuffer(); sb.append("["); for (Map<String, Object> each :
	 * list) { Iterator<String> it = each.keySet().iterator(); String key =
	 * it.next(); sb.append("'").append(key).append("'");//key sb }
	 * sb.append("]"); return sb.toString(); }
	 */

	/*
	 * ********************************************* private method
	 * *********************************************
	 */

	/**
	 * 
	 * @Title: appendWhereConditions
	 * @Description: 拼接where从句,并且返回实际参数
	 * @param map
	 * @return
	 * @return: Object[]
	 */
	private Object[] appendWhereClause(StringBuffer sql, Map<String, Object> map) {
		Assert.notEmpty(map, "AbstractDao.appendWhereClause.map为空,无法构成WHERE从句");
		Object[] objs = new Object[map.size()];
		Iterator<String> it = map.keySet().iterator();
		String firstField = it.next();
		sql.append(" WHERE ").append(firstField).append(" = ?");
		objs[0] = map.get(firstField);
		int index = 1;
		while (it.hasNext()) {
			String field = it.next();
			sql.append(" AND ").append(field).append(" = ?");
			objs[index] = map.get(field);
			index++;
		}
		return objs;
	}

	/**
	 * 
	 * @Title: appendOrderClause
	 * @Description: 拼接order by从句
	 * @param sql
	 * @param orderMap
	 * @return: void
	 */
	private void appendOrderClause(StringBuffer sql, LinkedHashMap<String, OrderType> orderMap) {
		if (CollectionUtils.isEmpty(orderMap))
			return;
		Iterator<String> it = orderMap.keySet().iterator();
		String firstOrderKey = it.next();
		sql.append(" ORDER BY ").append(firstOrderKey).append(" ").append(orderMap.get(firstOrderKey).getValue());
		while (it.hasNext()) {
			String orderKey = it.next();
			sql.append(", ").append(orderKey).append(" ").append(orderMap.get(firstOrderKey).getValue());
		}
	}

	protected void debug(String sql, Object[] args) {
		logger.debug(sql);
	}

}
