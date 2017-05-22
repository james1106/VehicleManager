package com.xpizza.vclemgr.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xpizza.core.anno.Json;
import com.xpizza.core.mvc.Data;
import com.xpizza.core.util.DateUtil;
import com.xpizza.plugin.echarts.ChartData;
import com.xpizza.vclemgr.service.EquipmentLossService;

/**
 * 车辆附件模块
 */
@Controller
@RequestMapping("equipmentLoss")
public class EquipmentLossController {

	static final Logger logger = LoggerFactory.getLogger(EquipmentLossController.class);

	@Resource
	private EquipmentLossService equipmentLossService;

	@Json
	@RequestMapping("eqPie.action")
	public Data eqPie() {
		Data data = new Data();
		try {
			List<ChartData> pie = equipmentLossService.drawPie();
			data.add("pie", pie);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

	@Json
	@RequestMapping("singleLine.action")
	public Data singleLine(String eqId) {
		Data data = new Data();
		try {
			Map<String, Integer> map = equipmentLossService.singleLine(eqId);
			if (CollectionUtils.isEmpty(map)) {
				data.add("yDataArr", new Integer[] {});
				data.add("xDataArr", new String[] {});
				return data;
			}
			data.add("yDataArr", map.values());
			Set<String> keySet = map.keySet();
			Iterator<String> it = keySet.iterator();
			int minLimit = Integer.parseInt(map.keySet().iterator().next());
			int maxLimit = minLimit;
			while (it.hasNext()) {
				String string = it.next();
				int num = Integer.parseInt(string);
				if (num < minLimit) {
					minLimit = num;
				}
				if (num > maxLimit) {
					maxLimit = num;
				}
			}
			List<String> xDataArr = new ArrayList<>();
			List<Date> datesBetweenTwoDate = getDatesBetweenTwoDate(
					DateUtil.stringToDate(String.valueOf(minLimit), DateUtil.FORMAT_BAR0),
					DateUtil.stringToDate(String.valueOf(maxLimit), DateUtil.FORMAT_BAR0));
			for (Date each : datesBetweenTwoDate) {
				xDataArr.add(DateUtil.dateToString(each, DateUtil.FORMAT_BAR0));
			}
			data.add("xDataArr", xDataArr);
		} catch (Throwable thr) {
			logger.warn(thr.getMessage());
			data.setError(thr.getMessage());
		}
		return data;
	}

	public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
		List lDate = new ArrayList();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}
}
