package com.cn.oa.business.mapper;


import com.cn.oa.domain.Depart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface DepartMapper {

    @Update("update depart set dstatus=#{dstatus} where did=#{did}")
    Integer deleteDepartByDid(@Param("did") Integer did,@Param("dstatus") Integer dstatus);

    @Update("update depart set dname=#{dname},duty=#{duty} where did=#{did}")
    Integer updateDepartByid(@Param("did")Integer did,@Param("dname") String dname,@Param("duty") String duty);

    @Insert("insert depart(dname,duty,dstatus,credate) value(#{dname},#{duty},0,#{date}) ")
    Integer addDepart(@Param("dname") String dname,@Param("duty") String duty,@Param("date") Date date);
    @Select("select DISTINCT dname, did from  depart where dstatus=1")
    List<Depart> selectAllDepart();


}
