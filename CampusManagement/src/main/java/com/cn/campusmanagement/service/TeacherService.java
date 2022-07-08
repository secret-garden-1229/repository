package com.cn.campusmanagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.campusmanagement.domain.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【tb_teacher】的数据库操作Service
* @createDate 2022-06-22 19:33:12
*/
public interface TeacherService extends IService<Teacher> {

    Teacher login(String name,String password);

    Teacher selectTeacherId(Long id);

    Page pagination(Page page,Teacher teacher);

}
