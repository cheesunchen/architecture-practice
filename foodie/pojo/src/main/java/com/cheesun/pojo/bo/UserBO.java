package com.cheesun.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author cheesun
 * @date 2020/11/14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "用户对象BO", description = "客户端传入的用户对象")
public class UserBO {

    @ApiModelProperty(value = "用户名", name = "username", example = "cheesun", required = true)
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;

    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123456")
    private String confirmPassword;
}
