package com.kang.controller.center;

import com.kang.BO.center.CenterUserBO;
import com.kang.VO.UserVO;
import com.kang.common.annotation.RequestParamRequired;
import com.kang.common.enums.ErrorMsgEnum;
import com.kang.common.enums.FileTypeEnum;
import com.kang.common.org.n3r.idworker.utils.UploadUtils;
import com.kang.common.utils.CookieUtils;
import com.kang.common.utils.JsonUtils;
import com.kang.common.utils.R;
import com.kang.pojo.UsersEntity;
import com.kang.service.center.CenterUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 个人中心 用户相关接口
 * @author kang
 * @email 784706982@qq.com
 * @date 2021-05-04 11:36:02
 */
@Api(value = "用户相关接口", tags = "个人中心用户相关接口")
@RestController
@RequestMapping("userInfo")
public class CenterUserController {

    @Autowired
    private CenterUsersService usersService;

    @Autowired
    private UploadUtils uploadUtils;

    @Value("${center.image.face}")
    private String facePath;

    /**
     * 根据用户id 修改 用户信息
     * @param userId 用户id
     * @param userBO 用户信息
     * @return 修改成功
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改用户信息", httpMethod = "POST", notes = "根据用户id修改用户信息")
    public R update(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParamRequired @RequestParam String userId,
            @RequestBody CenterUserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        UsersEntity usersEntity = usersService.updateUserInfo(userId, userBO);
        setUserCookie(usersEntity, request, response);
        return R.ok();
    }

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改", httpMethod = "POST")
    @PostMapping("uploadFace")
    public R uploadFace(
        @ApiParam(name = "userId", value = "用户id", required = true)
        @RequestParamRequired @RequestParam String userId,
        @ApiParam(name = "file", value = "用户头像", required = true)
        MultipartFile file,
        HttpServletRequest request, HttpServletResponse response) {
        String suffix = uploadUtils.getSuffix(file);
        //上传文件
        if (!FileTypeEnum.JPEG.getType().equalsIgnoreCase(suffix)
                && !FileTypeEnum.JPG.getType().equalsIgnoreCase(suffix)
                && !FileTypeEnum.PNG.getType().equalsIgnoreCase(suffix)) {
            return ErrorMsgEnum.ERROR_JPG.getR();
        }
        String url = uploadUtils.upload(file, facePath);
        usersService.lambdaUpdate()
                .set(UsersEntity::getFace, url)
                .eq(UsersEntity::getId, userId)
                .update();
        UsersEntity usersEntity = usersService.queryUserInfo(userId);
        setUserCookie(usersEntity, request, response);
        return R.ok();
    }

    private void setUserCookie(UsersEntity usersEntity, HttpServletRequest request, HttpServletResponse response) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(usersEntity, userVO);
        CookieUtils.setCookie(request, response,
                "user", JsonUtils.objectToJson(userVO),
                true);
    }
}
