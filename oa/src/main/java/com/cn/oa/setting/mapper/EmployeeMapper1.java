package com.cn.oa.setting.mapper;

import com.cn.oa.domain.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EmployeeMapper1 {

    @Select("select employee.*,depart.* from employee,depart where employee.did=depart.did and employee.ename=#{username} and employee.epass=#{password}")
    Employee login(@Param("username") String username, @Param("password") String password);

    @Update("update employee set  epass=#{password} where eid=#{id}")
    Integer updatePassword( @Param("password") String password ,@Param("id") Integer id);

}
