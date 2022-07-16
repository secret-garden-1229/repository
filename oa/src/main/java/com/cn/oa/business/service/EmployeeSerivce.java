package com.cn.oa.business.service;

import com.cn.oa.domain.Depart;
import com.cn.oa.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeSerivce {
    List<Employee> selectEmpoyee(Map<String,String> map);

    Integer deleteEmpoyeeById(Integer eid,Integer estatus );
    Integer addEmpoyee(Employee employee);
    List<Employee> selectPosition();
    Employee selectByPrimaryKey( String eid);
    Integer updateByPrimaryKeySelective(Employee employee);
}
