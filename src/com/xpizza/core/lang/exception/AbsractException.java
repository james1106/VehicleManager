package com.xpizza.core.lang.exception;

/** 自定义异常抽象类 */
@SuppressWarnings("serial")
public abstract class AbsractException extends Exception {

	public AbsractException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbsractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AbsractException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AbsractException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AbsractException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
