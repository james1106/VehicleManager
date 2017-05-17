package com.xpizza.core.lang.exception;

/** 数据超出范围 */
@SuppressWarnings("serial")
public class DataOutOfRangeException extends AbsractException {

	public DataOutOfRangeException() {
		super("数据超出范围");
		// TODO Auto-generated constructor stub
	}

	public DataOutOfRangeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DataOutOfRangeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataOutOfRangeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DataOutOfRangeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
