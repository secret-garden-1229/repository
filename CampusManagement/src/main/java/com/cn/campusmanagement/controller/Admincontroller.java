package com.cn.campusmanagement.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.campusmanagement.domain.Admin;
import com.cn.campusmanagement.mapper.AdminMapper;
import com.cn.campusmanagement.service.AdminService;
import com.cn.campusmanagement.utils.MD5;
import com.cn.campusmanagement.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/sms/adminController")
public class Admincontroller {

    @Resource
    private AdminService adminService;

    @GetMapping(value = "/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(@PathVariable Integer pageNo, @PathVariable Integer pageSize, String adminName){
        Page page=new Page(pageNo,pageSize);
        Admin admin=new Admin();
        admin.setName(adminName);
        Page list = adminService.pagination(page, admin);
        return Result.ok(list);
    }

    @PostMapping(value = "/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(@RequestBody Admin admin){
        admin.setPassword(MD5.encrypt(admin.getPassword()));
        boolean flag = adminService.saveOrUpdate(admin);
        if (flag){
            return Result.ok();
        }
        return Result.fail().message("系统繁忙，请稍后重试");
    }


    @DeleteMapping(value = "/deleteAdmin")
    public Result deleteAdmin(@RequestBody List<Integer> ids){
        boolean flag = adminService.removeByIds(ids);
        return Result.ok();
    }




}
