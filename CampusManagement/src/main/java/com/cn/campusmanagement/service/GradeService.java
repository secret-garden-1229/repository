package com.cn.campusmanagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.campusmanagement.domain.Grade;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_grade】的数据库操作Service
* @createDate 2022-06-22 19:32:54
*/
public interface GradeService extends IService<Grade> {

    Page pagination(Page page,Grade grade);

    List<Grade> getAllGrade();
}
