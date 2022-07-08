package com.cn.campusmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.campusmanagement.domain.Teacher;
import com.cn.campusmanagement.service.TeacherService;
import com.cn.campusmanagement.mapper.TeacherMapper;
import com.cn.campusmanagement.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_teacher】的数据库操作Service实现
* @createDate 2022-06-22 19:33:12
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher login(String name, String password) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("name",name);
        queryWrapper.eq("password", MD5.encrypt(password));
        List<Teacher> list = teacherMapper.selectList(queryWrapper);
        if (list.size()==1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Teacher selectTeacherId(Long id) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("id",id);
        return teacherMapper.selectOne(queryWrapper);
    }

    @Override
    public Page pagination(Page page, Teacher teacher) {
        QueryWrapper queryWrapper=new QueryWrapper();
        String className=teacher.getClazzName();
        String name =teacher.getName();
        if (!StringUtils.isEmpty(className)){
            queryWrapper.like("clazz_name",className);
        }

        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }

        Page list = teacherMapper.selectPage(page, queryWrapper);
        return list;
    }
}




