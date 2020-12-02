package com.cheesun.controller;

import com.cheesun.pojo.Users;
import com.cheesun.pojo.bo.UserBO;
import com.cheesun.service.UsersService;
import com.cheesun.utils.CookieUtils;
import com.cheesun.utils.IMOOCJSONResult;
import com.cheesun.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cheesun
 * @date 2020/11/14
 */
@Api(value = "注册登录", tags = {"用于注册登录相关的接口"})
@RestController
@RequestMapping("/passport/")
public class PassportController {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {
        // 1 用户名不能为空
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空！");
        }

        // 2 用户名是否存在
        boolean isExist = usersService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在！");
        }
        // 3 可用用户名返回成功
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("register")
    public IMOOCJSONResult register(@RequestBody UserBO userBO) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        // 1 判断用户名、密码不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }
        // 2 判断用户名是否存在
        boolean isExist = usersService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在！");
        }
        // 3 密码格式校验
        // 4 密码与确认密码必须一致
        if (!password.equals(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }

        // 5 开始注册
        Users user = usersService.createUser(userBO);
        return IMOOCJSONResult.ok(user);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        // 1 判断用户名、密码不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }

        // 5 检索用户密码是否匹配
        Users user = usersService.queryUser4Login(username, password);

        if (null == user) {
            return IMOOCJSONResult.errorMsg("用户名或密码不正确");
        }

        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        return IMOOCJSONResult.ok(user);
    }

    @ApiOperation(value = "用户登出", notes = "用户登出", httpMethod = "POST")
    @PostMapping("logout")
    public IMOOCJSONResult logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {

        // 清楚用户相关cookie
        CookieUtils.deleteCookie(request, response, "user");

        /**
         * TODO
         * 1 清除购物车
         * 2 分布式会话中清除用户数据
         */

        return IMOOCJSONResult.ok();
    }
}
