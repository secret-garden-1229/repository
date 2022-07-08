package com.cn.campusmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.campusmanagement.domain.Grade;
import com.cn.campusmanagement.service.GradeService;
import com.cn.campusmanagement.mapper.GradeMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_grade】的数据库操作Service实现
* @createDate 2022-06-22 19:32:54
*/
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade>
    implements GradeService {

    @Resource
    private GradeMapper gradeMapper;

    @Override
    public Page pagination(Page page, Grade grade) {
        QueryWrapper queryWrapper=new QueryWrapper();
        String name=grade.getName();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByAsc("id");
        Page list = gradeMapper.selectPage(page, queryWrapper);
        return list;
    }

    @Override
    public List<Grade> getAllGrade() {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.select("name");
        return gradeMapper.selectList(queryWrapper);
    }

}




