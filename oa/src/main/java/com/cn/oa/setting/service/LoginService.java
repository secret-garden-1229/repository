package com.cn.oa.setting.service;

import com.cn.oa.domain.Employee;


public interface LoginService {

    Employee login(String username, String password);

    Integer updatePassword (String password,Integer id);

}
