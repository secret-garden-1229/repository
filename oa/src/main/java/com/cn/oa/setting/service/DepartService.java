package com.cn.oa.setting.service;

import com.cn.oa.domain.Depart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartService {
    /**
     * 查询所有有效的部门
     * */
    List<Depart> selectAllByDstatus(@Param("dstatus") Integer dstatus);

}
