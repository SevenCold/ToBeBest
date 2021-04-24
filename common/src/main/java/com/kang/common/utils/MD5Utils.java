package com.kang.common.utils;

import com.kang.common.exception.KangException;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	/**
	 * 对字符串进行md5加密
	 * @param strValue 原始字符串
	 * @return 加密后的字符串
	 */
	public static String getMD5Str(String strValue) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			return Base64.encodeBase64String(md5.digest(strValue.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			throw new KangException("MD5加密失败");
		}
	}
}
