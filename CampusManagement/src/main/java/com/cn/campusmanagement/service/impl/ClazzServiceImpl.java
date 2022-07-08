package com.cn.campusmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.campusmanagement.domain.Clazz;
import com.cn.campusmanagement.service.ClazzService;
import com.cn.campusmanagement.mapper.ClazzMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_clazz】的数据库操作Service实现
* @createDate 2022-06-22 19:32:37
*/
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz>
    implements ClazzService {
    @Resource
    private ClazzMapper clazzMapper;
    @Override
    public Page getClazzsByOpr(Page page, Clazz clazz) {
        QueryWrapper queryWrapper=new QueryWrapper();
        String grade = clazz.getGradeName();
        String name = clazz.getName();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",clazz.getName());
        }
        if (!StringUtils.isEmpty(grade)){
            queryWrapper.like("grade_name",clazz.getGradeName());
        }
        Page list = clazzMapper.selectPage(page, queryWrapper);
         return list;
    }

    @Override
    public List<Clazz> getAllClazz() {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.select("name");
        return clazzMapper.selectList(queryWrapper);
    }
}




