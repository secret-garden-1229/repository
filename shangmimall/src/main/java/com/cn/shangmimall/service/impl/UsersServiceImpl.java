package com.cn.shangmimall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmimall.domain.Users;
import com.cn.shangmimall.service.UsersService;
import com.cn.shangmimall.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【users(用户)】的数据库操作Service实现
* @createDate 2022-07-19 15:24:03
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




