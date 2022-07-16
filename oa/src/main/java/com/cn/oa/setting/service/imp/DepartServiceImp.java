package com.cn.oa.setting.service.imp;

import com.cn.oa.domain.Depart;
import com.cn.oa.setting.mapper.DepartMapper1;
import com.cn.oa.setting.service.DepartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartServiceImp implements DepartService {

    @Resource
    private DepartMapper1 departMapper1;
    /**
     * 查询所有有效的部门
     * */
    @Override
    public List<Depart> selectAllByDstatus(Integer dstatus) {
        return departMapper1.selectAllByDstatus(dstatus);
    }
}
