package com.kang.BO;

import com.kang.common.validator.group.LoginGroup;
import com.kang.common.validator.group.RegistGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("用户注册前端form表单")
public class UserBO {

    @ApiModelProperty(notes = "用户名,长度小于10位", required = true, example = "kang")
    @NotBlank(groups = {RegistGroup.class, LoginGroup.class}, message = "用户名不能为空")
    @Length(max = 10, message = "用户名长度超过10位", groups = {RegistGroup.class, LoginGroup.class})
    private String username;

    @ApiModelProperty(notes = "密码，长度6-18位", required = true, example = "123456")
    @NotBlank(groups = {RegistGroup.class, LoginGroup.class}, message = "密码不能为空")
    @Length(min = 6, max = 18, message = "密码长度不符合6-18位", groups = {RegistGroup.class, LoginGroup.class})
    private String password;

    @ApiModelProperty(notes = "确认密码，长度6-18位", required = true, example = "123456")
    @NotBlank(groups = {RegistGroup.class}, message = "确认密码不能为空")
    @Length(min = 6, max = 18, message = "确认密码长度不符合6-18位", groups = {RegistGroup.class})
    private String confirmPassword;
}
