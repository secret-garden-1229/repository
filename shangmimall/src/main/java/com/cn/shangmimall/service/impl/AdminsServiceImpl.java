package com.cn.shangmimall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmimall.domain.Admins;
import com.cn.shangmimall.service.AdminsService;
import com.cn.shangmimall.mapper.AdminsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【admins(管理员)】的数据库操作Service实现
* @createDate 2022-07-19 15:21:34
*/
@Service
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins>
    implements AdminsService{

}




