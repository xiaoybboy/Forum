package com.xiao.exception;

/**
 * 用户已存在异常
 * 
 * @author XIAO
 *
 */
public class UserExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserExistException(String errorMessage) {
		super(errorMessage);
	}

}
