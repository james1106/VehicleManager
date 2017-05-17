package com.xpizza.core.lang.exception;

/** 期待一个值,而结果是多个值 */
@SuppressWarnings("serial")
public class NotUniqueException extends AbsractException {

	public NotUniqueException() {
		super("违反唯一性约束:期待返回一行数据,而实际返回多行");
		// TODO Auto-generated constructor stub
	}

	public NotUniqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotUniqueException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotUniqueException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotUniqueException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
