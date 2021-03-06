package com.kang.common.enums;

import cn.hutool.http.HttpStatus;
import com.kang.common.utils.R;

/**
 * 统一放置错误信息
 *  状态码：
 *      1.HTTP 状态码 三位数
 *      2.业务状态码 五位数
 *  业务状态码规范：
 *      1.系统相关40100 - 42000
 *      2.用户相关50100 - 52000
 *      3.数据库相关60100 - 62000
 * @author 汪少
 * @date 2021/4/8 13:55
 */
public enum ErrorMsgEnum {
    // ---------------HTTP 状态码 START------------------------
    INTERNAL_ERROR(HttpStatus.HTTP_INTERNAL_ERROR, "服务器异常，请联系管理员"),
    FORBIDDEN(HttpStatus.HTTP_FORBIDDEN, "无权访问"),

    // -----------登陆失败 统一为401状态码，便于前端处理 START --------------------

    // --------------登陆失败 END ------------------------------

    NOT_FOUND(HttpStatus.HTTP_NOT_FOUND, "路径不存在，请检查路径是否正确"),
    // ---------------HTTP 状态码 END--------------------------

    // ---------------业务 状态码 START------------------------
    // ---------------系统相关---------------------------------
    ERROR_PARAM(500, "参数错误"),
    //----------------用户和账号相关---------------------------
    NOT_SAME_PWD(500,"密码两次确认不一致"),
    USER_NOT_EXIST(500,"用户未注册"),
    USER_EXIST(500,"用户已注册"),
    EMPTY_NAME(500,"用户名不能为空"),
    ERROR_LOGIN(500,"用户名或密码错误"),
    FILE_EMPTY(40105, "上传文件不能为空"),
    ERROR_JPG(40106, "图片文件格式错误"),
    ERROR_FILE(40107, "图片文件格式错误"),
    FILE_UPLOAD_ERROR(40108, "文件上传失败"),
    TIME_PATTERN_ERROR(40109, "时间格式错误"),
    ORDER_NOT_EXIST(500, "订单不存在"),
    // ---------------数据库相关-----------------------------
    DUP_KEY(60100, "数据库中已存在该记录")
    // ---------------业务 状态码 END------------------------
    ;

    private final int status;
    private final String msg;

    ErrorMsgEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getDetailMsg(String detail) {
        return msg + "详细信息为：" + detail;
    }

    public int getStatus() {
        return status;
    }

    public R getR() {
        return R.error(status,  msg);
    }
}
