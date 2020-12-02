package com.cheesun.service.impl;

import com.cheesun.enums.SexEnum;
import com.cheesun.mapper.UsersMapper;
import com.cheesun.pojo.Users;
import com.cheesun.pojo.bo.UserBO;
import com.cheesun.service.UsersService;
import com.cheesun.utils.DateUtil;
import com.cheesun.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.TreeSet;

/**
 * @author cheesun
 * @date 2020/11/14
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    private static final String DEFAULT_DATE = "1900-01-01";

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users getUserById(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {


        TreeSet set = new TreeSet();


        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);

        Users user = usersMapper.selectOneByExample(userExample);
        return user != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) throws Exception {

        // 全局唯一id
        String userId = sid.nextShort();

        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        // 默认昵称同用户名
        user.setNickname(userBO.getUsername());
        // 默认头像
        user.setFace(USER_FACE);
        // 默认生日
        user.setBirthday(DateUtil.stringToDate(DEFAULT_DATE));
        // 默认性别保密
        user.setSex(SexEnum.SECRET.type);

        // 设置当前时间
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUser4Login(String username, String password) throws Exception {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", MD5Utils.getMD5Str(password));

        return usersMapper.selectOneByExample(userExample);
    }
}
