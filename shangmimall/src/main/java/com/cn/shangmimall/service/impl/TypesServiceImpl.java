package com.cn.shangmimall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmimall.domain.Types;
import com.cn.shangmimall.service.TypesService;
import com.cn.shangmimall.mapper.TypesMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【types(分类)】的数据库操作Service实现
* @createDate 2022-07-19 15:23:52
*/
@Service
public class TypesServiceImpl extends ServiceImpl<TypesMapper, Types>
    implements TypesService{

}




