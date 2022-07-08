package com.cn.campusmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.campusmanagement.domain.Student;
import com.cn.campusmanagement.service.StudentService;
import com.cn.campusmanagement.mapper.StudentMapper;
import com.cn.campusmanagement.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_student】的数据库操作Service实现
* @createDate 2022-06-22 19:33:04
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    public  Student login(String name,String password){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("name",name);
        queryWrapper.eq("password", MD5.encrypt(password));
        List<Student> list = studentMapper.selectMaps(queryWrapper);
        if (list.size()==1){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Student selectStudentById(Long id) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("id",id);
        return studentMapper.selectOne(queryWrapper);
    }

    @Override
    public Page pagination(Page page, Student student) {
        String name=student.getName();
        String studentClass=student.getClazzName();
        QueryWrapper queryWrapper=new QueryWrapper();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(studentClass)){
            queryWrapper.like("clazz_name",studentClass);
        }
        Page list = studentMapper.selectPage(page, queryWrapper);
        return list;
    }

}




