
package com.kang.common.utils;

import cn.hutool.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author kang
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("status", 200);
		put("msg", "success");
	}
	
	public static R error() {
		return error(HttpStatus.HTTP_INTERNAL_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.HTTP_INTERNAL_ERROR, msg);
	}
	
	public static R error(int status, String msg) {
		R r = new R();
		r.put("status", status);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public static R ok(Object obj) {
		Map<String, Object> res = new HashMap<>();
		res.put("data", obj);
		return R.ok(res);
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
