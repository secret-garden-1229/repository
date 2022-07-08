package com.cn.campusmanagement.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.campusmanagement.domain.Clazz;
import com.cn.campusmanagement.service.ClazzService;
import com.cn.campusmanagement.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;

@RequestMapping(value = "/sms/clazzController")
@RestController
public class ClazzController {

    @Resource
    private ClazzService clazzService;

    @GetMapping(value = "/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(@PathVariable Integer pageNo, @PathVariable Integer pageSize,Clazz clazz){
        Page page=new Page(pageNo,pageSize);
        Page list = clazzService.getClazzsByOpr(page, clazz);
        return Result.ok(list);
    }

    @GetMapping(value = "/getClazzs")
    public  Result getClazzs(){
        return Result.ok(clazzService.getAllClazz());
    }

}
