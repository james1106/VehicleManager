package com.xpizza.core.lang;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.xpizza.core.lang.exception.TypeCastException;

/** 数字操作工具 */
public class NumberUtil {

	/** Object转Integer */
	public static Integer toInt(Object obj) throws TypeCastException {
		if (obj == null) {
			return null;
		}
		if (obj instanceof java.lang.Number) {
			return ((java.lang.Number) obj).intValue();
		} else if (obj instanceof java.lang.String) {
			return Integer.parseInt((java.lang.String) obj);
		} else {
			throw new TypeCastException("目标转成Integer类型失败!");
		}
	}

	/** Object转为int,为空则返回0 */
	public static int toIntWithZero(Object obj) throws TypeCastException {
		if (obj == null) {
			return 0;
		}
		return toInt(obj);
	}

	/** Object转为BigDecimal,默认为0 */
	public static BigDecimal toBigDecimalWithZero(Object obj) {
		String str = StringUtil.toStringWithEmpty(obj);
		return StringUtil.isEmpty(str) ? BigDecimal.ZERO : new BigDecimal(obj.toString());
	}

	/** 保留有效数字,并四舍五入 */
	public static BigDecimal retainDecimal(BigDecimal bigDecimal, int places) {
		if (null == bigDecimal || BigDecimal.ZERO.equals(bigDecimal))
			return BigDecimal.ZERO;
		return bigDecimal.setScale(places, RoundingMode.HALF_UP);
	}

}
