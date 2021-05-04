package com.kang.controller.center;

import com.kang.common.annotation.RequestParamRequired;
import com.kang.common.utils.R;
import com.kang.service.center.CenterUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个人中心接口
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-05-04 11:36:02
 */
@Api(value = "个人中心", tags = "个人中心接口")
@RestController
@RequestMapping("center")
public class CenterController {

    @Autowired
    private CenterUsersService usersService;

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    @GetMapping("/userInfo")
    @ApiOperation(value = "查询用户信息", httpMethod = "GET", notes = "根据用户id查询用户信息")
    public R userInfo(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParamRequired @RequestParam String userId) {
        return R.ok(usersService.queryUserInfo(userId));
    }
}
