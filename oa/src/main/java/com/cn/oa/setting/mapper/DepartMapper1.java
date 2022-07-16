package com.cn.oa.setting.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cn.oa.domain.Depart;



public interface DepartMapper1 {

    /**
     * 查询所有有效的部门
     * */
    List<Depart> selectAllByDstatus(@Param("dstatus") Integer dstatus);


}
