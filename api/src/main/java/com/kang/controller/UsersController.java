package com.kang.controller;

import com.kang.BO.UserBO;
import com.kang.VO.UserVO;
import com.kang.common.annotation.RequiredParam;
import com.kang.common.enums.ErrorMsgEnum;
import com.kang.common.utils.CookieUtils;
import com.kang.common.utils.JsonUtils;
import com.kang.common.utils.R;
import com.kang.common.validator.ValidatorUtils;
import com.kang.common.validator.group.LoginGroup;
import com.kang.common.validator.group.RegistGroup;
import com.kang.pojo.UsersEntity;
import com.kang.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户表 
 *
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-04-24 10:11:02
 */
@Api(value = "用户和账号管理", tags = "用户和账号管理相关接口")
@RestController
@RequestMapping("passport")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * 用户注册
     * @param userBO 注册信息
     * @return
     */
    @ApiOperation(value = "用户注册", httpMethod = "POST", notes = "用户注册接口，包括用户名，密码和确认密码信息")
    @PostMapping("regist")
    public R register(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        ValidatorUtils.validateEntity(userBO, RegistGroup.class);
        if (!userBO.getPassword().equals(userBO.getConfirmPassword())) {
            return ErrorMsgEnum.NOT_SAME_PWD.getR();
        }
        boolean exist = usersService.queryUserNameExist(userBO.getUsername());
        if (exist) {
            return ErrorMsgEnum.USER_EXIST.getR();
        }
        UsersEntity usersEntity = usersService.insertUser(userBO);
        setCookie(usersEntity, request, response);
        return R.ok();
    }

    /**
     * 根据用户名判断用户是否存在
     * @param username 用户名
     * @return
     */
    @ApiOperation(value = "根据用户名判断用户是否存在", httpMethod = "GET", notes = "根据用户名判断用户是否存在")
    @GetMapping("/usernameIsExist")
    public R queryUserNameExist(@RequiredParam @RequestParam String username) {
        boolean exist = usersService.queryUserNameExist(username);
        if (exist) {
            return ErrorMsgEnum.USER_EXIST.getR();
        } else {
            return R.ok();
        }
    }

    /**
     * 用户登录
     * @param userBO 注册信息
     * @return 登录成功的用户信息
     */
    @ApiOperation(value = "用户登录", httpMethod = "POST", notes = "用户登录，包括用户名，密码信息")
    @PostMapping("login")
    public R login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        ValidatorUtils.validateEntity(userBO, LoginGroup.class);
        UsersEntity usersEntity = usersService.queryUserLogin(userBO.getUsername(), userBO.getPassword());
        if (usersEntity == null) {
            return ErrorMsgEnum.ERROR_LOGIN.getR();
        }
        setCookie(usersEntity, request, response);
        return R.ok(usersEntity);
    }

    /**
     * 根据用户id退出登录
     * @param userId 用户ID
     * @return 退出登录成功信息
     */
    @ApiOperation(value = "用户登出", httpMethod = "GET", notes = "根据用户id退出登录")
    @PostMapping("/logout")
    public R logout(@RequiredParam@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "user");
        return R.ok();
    }

    /**
     * 设置cookie值
     * @param usersEntity 用户信息，存入cookie的值
     * @param request 请求
     * @param response 响应
     */
    private void setCookie(UsersEntity usersEntity, HttpServletRequest request, HttpServletResponse response) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(usersEntity, userVO);
        CookieUtils.setCookie(request, response,
                "user", JsonUtils.objectToJson(userVO),
                true);
    }

}
