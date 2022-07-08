package com.cn.campusmanagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.campusmanagement.domain.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tb_clazz】的数据库操作Service
* @createDate 2022-06-22 19:32:37
*/
public interface ClazzService extends IService<Clazz> {
        Page getClazzsByOpr(Page page,Clazz clazz);
        List<Clazz> getAllClazz();

}
