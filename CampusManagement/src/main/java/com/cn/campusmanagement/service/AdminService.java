package com.cn.campusmanagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.campusmanagement.domain.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【tb_admin】的数据库操作Service
* @createDate 2022-06-22 19:32:10
*/
public interface AdminService extends IService<Admin> {
    Admin login(String name,String password);

    Admin selectAdminByID(Long id);

    Page pagination(Page page,Admin admin);
}
