package com.cn.shangmimall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.shangmimall.domain.Goods;
import com.cn.shangmimall.service.GoodsService;
import com.cn.shangmimall.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【goods(商品)】的数据库操作Service实现
* @createDate 2022-07-19 15:22:50
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

}




