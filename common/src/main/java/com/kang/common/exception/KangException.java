package com.kang.common.exception;

/**
 * 自定义异常
 *
 * @author kang
 */
public class KangException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int status = 500;
    
    public KangException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public KangException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public KangException(String msg, int status) {
		super(msg);
		this.msg = msg;
		this.status = status;
	}
	
	public KangException(String msg, int status, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
