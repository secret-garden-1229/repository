package com.cn.campusmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.campusmanagement.domain.Admin;
import com.cn.campusmanagement.service.AdminService;
import com.cn.campusmanagement.mapper.AdminMapper;
import com.cn.campusmanagement.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_admin】的数据库操作Service实现
* @createDate 2022-06-22 19:32:10
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin login(String name, String password) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("name",name);
        queryWrapper.eq("password", MD5.encrypt(password));
        List<Admin> admin = adminMapper.selectList(queryWrapper);
        if (admin.size()==1){
            return admin.get(0);
        }
        return null;
    }

    @Override
    public Admin selectAdminByID(Long id) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("id",id);
        return adminMapper.selectOne(queryWrapper);
    }

    @Override
    public Page pagination(Page page, Admin admin) {
        QueryWrapper queryWrapper=new QueryWrapper();
        String name=admin.getName();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        Page list = adminMapper.selectPage(page, queryWrapper);
        return list;
    }
}




