package com.xpizza.core.lang.exception;

/** 类型转化异常 */
@SuppressWarnings("serial")
public class TypeCastException extends AbsractException {

	public TypeCastException() {
		super("类型转化失败");
		// TODO Auto-generated constructor stub
	}

	public TypeCastException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TypeCastException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TypeCastException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TypeCastException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
