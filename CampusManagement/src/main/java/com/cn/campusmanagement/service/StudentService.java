package com.cn.campusmanagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.campusmanagement.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.java.Log;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_student】的数据库操作Service
* @createDate 2022-06-22 19:33:04
*/
public interface StudentService extends IService<Student> {

    Student login(String name,String password);
    Student selectStudentById(Long id);
    Page pagination(Page page, Student student);
}
