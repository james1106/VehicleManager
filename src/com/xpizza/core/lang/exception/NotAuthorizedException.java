package com.xpizza.core.lang.exception;

/** 未被授权 */
@SuppressWarnings("serial")
public class NotAuthorizedException extends AbsractException {

	public NotAuthorizedException() {
		super("未被授权");
		// TODO Auto-generated constructor stub
	}

	public NotAuthorizedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotAuthorizedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotAuthorizedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotAuthorizedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
