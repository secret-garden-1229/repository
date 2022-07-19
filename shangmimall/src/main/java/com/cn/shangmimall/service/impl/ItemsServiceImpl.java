package com.cn.shangmimall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmimall.domain.Items;
import com.cn.shangmimall.service.ItemsService;
import com.cn.shangmimall.mapper.ItemsMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【items(订单项)】的数据库操作Service实现
* @createDate 2022-07-19 15:23:15
*/
@Service
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items>
    implements ItemsService{

}




