package org.zuel.crm.settings.service;

import org.zuel.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 根据账号密码登录
     * @param map
     * @return
     */
    User queryUserByLoginActAndPwd(Map<String,Object> map);

    /**
     * 查询所有未锁定的用户
     * @return 查询到的用户列表
     */
    List<User> queryAllUsers();
}
