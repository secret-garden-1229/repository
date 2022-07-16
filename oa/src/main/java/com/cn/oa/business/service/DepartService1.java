package com.cn.oa.business.service;

import com.cn.oa.domain.Depart;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DepartService1 {

    Integer deleteDepartByDid( Integer did,Integer dstatus);

    Integer updateDepartByDid(Integer did,String dnamd,String duty);

    Integer addSelective(String dname,String duty, Date date);

    List<Depart> selectAllDepart();
}
