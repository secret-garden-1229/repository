package org.zuel.crm.settings.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.crm.settings.domain.User;
import org.zuel.crm.settings.mapper.UserMapper;
import org.zuel.crm.settings.service.UserService;

import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据密码账号查询用户
     * @param map
     * @return
     */
    @Override
    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }


    /**
     * 查询所有未锁定的用户
     * @return 查询到的用户列表
     */
    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectAllUsers();
    }


}
