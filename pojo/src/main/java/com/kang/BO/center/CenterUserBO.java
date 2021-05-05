package com.kang.BO.center;

import com.kang.common.annotation.Mobile;
import com.kang.common.validator.group.center.CenterUserGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/** 个人中心用户bo
 * @author kang
 */
@Data
@ApiModel(value="用户对象", description="个人中心用户bo")
public class CenterUserBO {

    @ApiModelProperty(value="用户名", name="username", example="张三")
    private String username;
    @ApiModelProperty(value="密码", name="password", example="123456")
    private String password;
    @ApiModelProperty(value="确认密码", name="confirmPassword", example="123456")
    private String confirmPassword;

    @NotBlank(message = "用户昵称不能为空", groups = {CenterUserGroup.class})
    @Length(max = 12, message = "用户昵称不能超过12位")
    @ApiModelProperty(value="用户昵称", name="nickname", example="小四")
    private String nickname;

    @Length(max = 12, message = "用户真实姓名不能超过12位", groups = {CenterUserGroup.class})
    @ApiModelProperty(value="真实姓名", name="realname", example="张三")
    private String realname;

    @ApiModelProperty(value="手机号", name="mobile", example="13912345678")
    @Mobile(groups = {CenterUserGroup.class})
    private String mobile;

    @Email(groups = {CenterUserGroup.class})
    @ApiModelProperty(value="邮箱地址", name="email", example="kf@qq.com")
    private String email;

    @Range(max = 2, min = 0, message = "性别选择错误", groups = {CenterUserGroup.class})
    @ApiModelProperty(value="性别", name="sex", example="0:女 1:男 2:保密")
    private Integer sex;

    @ApiModelProperty(value="生日", name="birthday", example="1900-01-01")
    private Date birthday;
}