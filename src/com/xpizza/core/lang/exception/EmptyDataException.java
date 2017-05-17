package com.xpizza.core.lang.exception;

/**
 * 
 * @ClassName: EmptyDataException 
 * @Description: 空数据异常
 * @author: Xpizza
 * @date: Mar 31, 2017 5:31:28 PM
 */
@SuppressWarnings("serial")
public class EmptyDataException extends AbsractException {

	public EmptyDataException() {
		super("没有意义的空数据");
		// TODO Auto-generated constructor stub
	}

	public EmptyDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmptyDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmptyDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmptyDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}