package com.cn.oa.business.service.imp;

import com.cn.oa.business.mapper.DepartMapper;
import com.cn.oa.business.service.DepartService1;
import com.cn.oa.domain.Depart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DepartServiceImp1 implements DepartService1 {

    @Resource
    private DepartMapper departMapper;


    @Override
    public Integer updateDepartByDid(Integer did, String dnamd, String duty) {
        return departMapper.updateDepartByid(did,dnamd,duty);
    }

    @Override
    public Integer addSelective(String dname, String duty, Date date) {
        return departMapper.addDepart( dname, duty,date);
    }

    @Override
    public List<Depart> selectAllDepart() {
        return departMapper.selectAllDepart();
    }

    @Override
    public Integer deleteDepartByDid(Integer did,Integer dstatus) {
        return departMapper.deleteDepartByDid(did,dstatus);
    }
}
