package com.cn.oa.business.service.imp;

import com.cn.oa.business.mapper.EmployeeMapper;
import com.cn.oa.business.service.EmployeeSerivce;
import com.cn.oa.domain.Depart;
import com.cn.oa.domain.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImp implements EmployeeSerivce {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> selectEmpoyee(Map<String, String> map) {
        return employeeMapper.selectEmpoyee(map);
    }

    @Override
    public Integer deleteEmpoyeeById(Integer eid, Integer estatus) {
        return employeeMapper.deleteEmpoyeeById(eid,estatus);
    }

    @Override
    public Integer addEmpoyee(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public List<Employee> selectPosition() {
        return employeeMapper.selectPosition();
    }

    @Override
    public Employee selectByPrimaryKey(String eid) {
        return employeeMapper.selectEmpoleeById(eid);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }
}
