
package com.kang.common.utils;

import cn.hutool.http.HttpStatus;

import java.util.HashMap;

/**
 * 返回数据
 *
 * @author kang
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	private static final String MSG_KEY = "msg";

	private static final String DATA_KEY = "data";

	private static final String STATUS_KEY = "status";
	
	public R() {
		put(STATUS_KEY, 200);
		put(MSG_KEY, "OK");
	}

	public R(int status, String msg) {
		put(STATUS_KEY, status);
		put(MSG_KEY, msg);
	}
	
	public static R error() {
		return error(HttpStatus.HTTP_INTERNAL_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.HTTP_INTERNAL_ERROR, msg);
	}
	
	public static R error(int status, String msg) {
		return new R(status, msg);
	}
	
	public static R ok() {
		return new R();
	}

	public static R ok(Object obj) {
		R r = new R();
		r.put(DATA_KEY, obj);
		return r;
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
