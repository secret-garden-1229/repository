package com.cn.oa.business.mapper;

import com.cn.oa.domain.Depart;
import com.cn.oa.domain.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    List<Employee> selectEmpoyee(Map<String,String> map);

    @Update("update employee set estatus=#{estatus},leavedate= now() where eid=#{eid}")
    Integer deleteEmpoyeeById(@Param("eid") Integer eid,@Param("estatus") Integer estatus );

    Integer insert(Employee employee);

    @Select("select DISTINCT position from  employee")
    List<Employee> selectPosition();

    Employee selectEmpoleeById(@Param("eid") String eid);

    Integer updateByPrimaryKeySelective(Employee employee);
    

}
