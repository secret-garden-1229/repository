package com.cn.oa.setting.service.imp;

import com.cn.oa.domain.Employee;
import com.cn.oa.setting.mapper.EmployeeMapper1;
import com.cn.oa.setting.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImp implements LoginService {

    @Resource
    private EmployeeMapper1 employeeMapper1;

    public Employee login(String username, String password){
        return employeeMapper1.login(username,password);
    }

    @Override
    public Integer updatePassword(String password, Integer id) {
        return employeeMapper1.updatePassword(password,id);
    }

}
