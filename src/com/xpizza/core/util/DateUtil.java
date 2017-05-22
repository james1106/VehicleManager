package com.xpizza.core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.xpizza.core.lang.exception.DataOutOfRangeException;

/**
 * 日期工具
 */
public class DateUtil {

	public static final String FORMAT_BAR0 = "yyyyMMdd";

	/** 格式:年月日以横杠("-")分隔;精确到:年月日 */
	public static final String FORMAT_BAR1 = "yyyy-MM-dd";

	/** 格式:年月日以横杠("-")分隔;精确到:年月日时分 */
	public static final String FORMAT_BAR2 = "yyyy-MM-dd HH:mm";

	/** 格式:年月日以横杠("-")分隔;精确到:年月日时分秒 */
	public static final String FORMAT_BAR3 = "yyyy-MM-dd HH:mm:ss";

	/** 格式:年月日以横杠("-")分隔;精确到:年月日时分秒毫秒 */
	public static final String FORMAT_BAR4 = "yyyy-MM-dd HH:mm:ss:SSS";

	/** 格式:年月日以斜杠("/")分隔;精确到:年月日 */
	public static final String FORMAT_SLASH1 = "yyyy/MM/dd";

	/** 格式:年月日以斜杠("/")分隔;精确到:年月日时分 */
	public static final String FORMAT_SLASH2 = "yyyy/MM/dd HH:mm";

	/** 格式:年月日以斜杠("/")分隔;精确到:年月日时分秒 */
	public static final String FORMAT_SLASH3 = "yyyy/MM/dd HH:mm:ss";

	/** 格式:年月日以斜杠("/")分隔;精确到:年月日时分秒毫秒 */
	public static final String FORMAT_SLASH4 = "yyyy/MM/dd HH:mm:ss:SSS";

	/** 格式:时分秒 */
	public static final String FORMAT_CLOCK = "HH:mm:ss";

	/** 格式:中文形式的年月日 */
	public static final String FORMAT_CHN1 = "yyyy年MM月dd日";

	/**
	 * 日期转字符串,可自由选择格式(建议使用)
	 */
	public static String dateToString(Date date, String mode) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(mode);
		return dateFormat.format(date);
	}

	/**
	 * 字符串转日期,可自由选择格式(建议使用)
	 */
	public static Date stringToDate(String dateStr, String mode) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(mode);
		return sdf.parse(dateStr);
	}

	/**
	 * 将date转成String 格式：yyyy-MM-dd
	 */
	public static String getStrOfYMD(Date date) {
		return dateToString(date, FORMAT_BAR1);
	}

	/**
	 * 将String转成date 格式：yyyy-MM-dd
	 */
	public static Date getDateForYMD(String dateStr) throws ParseException {
		return stringToDate(dateStr, FORMAT_BAR1);
	}

	/**
	 * 转为sql.date,适用于插入数据库
	 */
	public static java.sql.Date getSqlDate(String dateStr) {
		return java.sql.Date.valueOf(dateStr);
	}

	/**
	 * 获取日期 格式：yyyy-MM-dd
	 */
	public static String getStrOfYMD(int fromToday) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, fromToday);
		return dateToString(calendar.getTime(), FORMAT_BAR1);
	}

	/**
	 * 获取当前时间的字符串,格式自选
	 */
	public static String getStrOfCurrentDate(String mode) {
		return dateToString(new Date(), mode);
	}

	/**
	 * 取当前年份
	 */
	public static int getCurrentYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取年周数 格式：201604
	 */
	public static String getWeekOfYear() {
		Calendar c = Calendar.getInstance();
		int weekNum = c.get(Calendar.WEEK_OF_YEAR);
		String weekStr = null;
		if (weekNum < 10) {
			weekStr = "0" + Integer.toString(weekNum);
		} else {
			weekStr = Integer.toString(weekNum);
		}
		String weekOfYear = c.get(Calendar.YEAR) + weekStr;
		return weekOfYear;
	}

	/**
	 * 根据输入的日期(格式是yyyy-MM-dd)计算年龄
	 */
	public static int getAge(String nyr) {
		String currentDate = getStrOfYMD(0).replace("-", "");
		nyr = nyr.replace("-", "");
		// 今天的年份
		int year = Integer.parseInt(currentDate.substring(0, 4));
		// 今天的月日
		int monthAndDay = Integer.parseInt(currentDate.substring(4));
		// 输入的年份
		int yearIn = Integer.parseInt(nyr.substring(0, 4));
		// 输入的月日
		int monthAndDayIn = Integer.parseInt(nyr.substring(4));
		// 计算年龄
		int age = year - yearIn;
		if (monthAndDay < monthAndDayIn)
			age = age - 1;
		return age;
	}

	/**
	 * 判断输入日期是星期几(格式要满足yyyy-MM-dd)
	 */
	public static int getWeekIndex(String dateStr) throws ParseException {
		Date date = getDateForYMD(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 西方世界中的一个星期是从星期日开始计数的,需要减1
		int chnWeekNum = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (chnWeekNum == 0) // 星期日
			chnWeekNum = 7;
		return chnWeekNum;
	}

	/**
	 * 按输入年月计算这个月有多少天
	 */
	public static int getDaysCountInMonth(int year, int month) {
		if (month < 1 || month > 12) {
			throw new DataOutOfRangeException("月份应在1~12之间");
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);// Java月份才0开始算
		return cal.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 获取时间戳
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
}