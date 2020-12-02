package com.cheesun.service;

import com.cheesun.pojo.Users;
import com.cheesun.pojo.bo.UserBO;

/**
 * @author cheesun
 * @date 2020/11/14
 */
public interface UsersService {

    /**
     * @param id
     * @return 用户对象
     */
    Users getUserById(Integer id);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBO
     * @return
     */
    Users createUser(UserBO userBO) throws Exception;

    /**
     * 检索用户名密码是否匹配，用于登录
     * @param username
     * @param password
     * @return
     */
    Users queryUser4Login(String username, String password) throws Exception;
}
