package com.cn.campusmanagement.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.campusmanagement.domain.Teacher;
import com.cn.campusmanagement.service.TeacherService;
import com.cn.campusmanagement.utils.MD5;
import com.cn.campusmanagement.utils.Result;
import jdk.nashorn.internal.runtime.ListAdapter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @GetMapping(value = "getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(@PathVariable Integer pageNo, @PathVariable Integer pageSize, Teacher teacher){
        Page page=new Page(pageNo,pageSize);
        Page page1 = teacherService.pagination(page, teacher);
        return  Result.ok(page1);
    }

    @PostMapping(value = "/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@RequestBody Teacher teacher){
        teacher.setPassword(MD5.encrypt(teacher.getPassword()));
        boolean flag = teacherService.saveOrUpdate(teacher);
        if (flag){
            return Result.ok();
        }
        return Result.fail().message("系统繁忙，请稍后重试！");
    }

    @DeleteMapping(value = "/deleteTeacher")
    public Result deleteTeacher(@RequestBody List<Integer> ids){
        boolean flag = teacherService.removeByIds(ids);
        if (flag){
            return Result.ok();
        }
        return Result.fail().message("系统繁忙，请稍后重试！");
    }

}
