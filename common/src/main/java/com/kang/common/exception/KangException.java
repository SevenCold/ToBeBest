package com.kang.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 * @author kang
 */
@Getter
@Setter
public class KangException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int status = 500;

    public KangException(String msg) {
		super(msg);
		this.msg = msg;
	}
	public KangException(String msg, int status) {
		super(msg);
		this.msg = msg;
		this.status = status;
	}
}
