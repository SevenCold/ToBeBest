package com.kang.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 处理Cookie
 */
public final class CookieUtils {

    final static Logger logger = LoggerFactory.getLogger(CookieUtils.class);

	/**
	 *
	 * @Description: 得到Cookie的值, 不编码
	 * @param request 请求
	 * @param cookieName cookie名称
	 * @return cookie名称对应值
	 */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     *
     * @Description: 得到Cookie的值
     * @param request 请求
     * @param cookieName cookie名称
     * @param isDecoder 是否需要UTF-8编码解码
     * @return cookie名称对应值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } else {
                        retValue = cookie.getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     *
     * @Description: 得到Cookie的值
     * @param request 请求
     * @param cookieName cookie名称
     * @param encodeString 解码的编码名称
     * @return cookie名称对应值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookie.getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
        	 e.printStackTrace();
        }
        return retValue;
    }

    /**
     *
     * @Description: 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     * @param request 请求
     * @param response 响应
     * @param cookieName cookie名称
     * @param cookieValue 名称对应的值
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     *
     * @Description: 设置Cookie的值 在指定时间内生效,但不编码
     * @param request 请求
     * @param response 响应
     * @param cookieName cookie名称
     * @param cookieValue 名称对应的值
     * @param cookieMaxAge 最大生效时间
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxAge) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxAge, false);
    }

    /**
     * 
     * @Description: 设置Cookie的值 不设置生效时间,但编码
     * 在服务器被创建，返回给客户端，并且保存客户端
     * 如果设置了SETMAXAGE(int seconds)，会把cookie保存在客户端的硬盘中
     * 如果没有设置，会默认把cookie保存在浏览器的内存中
     * 一旦设置setPath()：只能通过设置的路径才能获取到当前的cookie信息
     * @param request 请求
     * @param response 响应
     * @param cookieName cookie名称
     * @param cookieValue 名称对应的值
     * @param isEncode 是否编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

   /**
    * 
    * @Description: 设置Cookie的值 在指定时间内生效, 编码参数
    * @param request 请求
    * @param response 响应
    * @param cookieName cookie名称
    * @param cookieValue 名称对应的值
    * @param cookieMaxAge 最大生效时间
    * @param isEncode 是否编码
    */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxAge, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, isEncode);
    }

    /**
     * 
     * @Description: 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
     * @param request 请求
     * @param response 响应
     * @param cookieName cookie名称
     * @param cookieValue 名称对应的值
     * @param cookieMaxAge 最大生效时间
     * @param encodeString 编码名称
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxAge, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, encodeString);
    }

    /**
     *
     * @Description: 删除Cookie带cookie域名
     * @param request 请求
     * @param response 响应
     * @param cookieName cookie名称
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieName) {
        doSetCookie(request, response, cookieName, null, -1, false);
//        doSetCookie(request, response, cookieName, "", -1, false);
    }

    
    /**
     * 
     * @Description: 设置Cookie的值，并使其在指定时间内生效
     * @param request 请求
     * @param response 响应
     * @param cookieName cookie名称
     * @param cookieValue 名称对应的值
     * @param cookieMaxAge 最大生效时间
     * @param isEncode 是否编码
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName, String cookieValue, int cookieMaxAge, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxAge > 0)
                cookie.setMaxAge(cookieMaxAge);
            if (null != request) {// 设置域名的cookie
            	String domainName = getDomainName(request);
                logger.info("========== domainName: {} ==========", domainName);
                if (!"localhost".equals(domainName)) {
                	cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }

    /**
     * 
     * @Description: 设置Cookie的值，并使其在指定时间内生效
     * @param request 请求
     * @param response 响应
     * @param cookieName cookie名称
     * @param cookieValue 名称对应的值
     * @param cookieMaxAge 最大生效时间
     * @param encodeString 编码名称
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName, String cookieValue, int cookieMaxAge, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxAge > 0)
                cookie.setMaxAge(cookieMaxAge);
            if (null != request) {// 设置域名的cookie
            	String domainName = getDomainName(request);
                logger.info("========== domainName: {} ==========", domainName);
                if (!"localhost".equals(domainName)) {
                	cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }

    /**
     * 
     * @Description: 得到cookie的域名
     * @return cookie的域名
     */
    private static String getDomainName(HttpServletRequest request) {
        String domainName;

        String serverName = request.getRequestURL().toString();
        if (StringUtils.isBlank(serverName)) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            if (serverName.indexOf(":") > 0) {
            	String[] ary = serverName.split("\\:");
            	serverName = ary[0];
            }

            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3 && !isIp(serverName)) {
            	// www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }
        return domainName;
    }
    
    public static String trimSpaces(String IP){//去掉IP字符串前后所有的空格  
        while(IP.startsWith(" ")){  
               IP= IP.substring(1).trim();
            }  
        while(IP.endsWith(" ")){  
               IP= IP.substring(0,IP.length()-1).trim();  
            }  
        return IP;  
    }  
    
    public static boolean isIp(String IP){//判断是否是一个IP  
        boolean b = false;  
        IP = trimSpaces(IP);  
        if(IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")){  
            String[] s = IP.split("\\.");
            if(Integer.parseInt(s[0])<255)  
                if(Integer.parseInt(s[1])<255)  
                    if(Integer.parseInt(s[2])<255)  
                        if(Integer.parseInt(s[3])<255)  
                            b = true;  
        }  
        return b;  
    }  

}
