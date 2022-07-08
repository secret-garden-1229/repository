package com.cn.campusmanagement.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.campusmanagement.domain.Student;
import com.cn.campusmanagement.service.ClazzService;
import com.cn.campusmanagement.service.GradeService;
import com.cn.campusmanagement.service.StudentService;
import com.cn.campusmanagement.utils.MD5;
import com.cn.campusmanagement.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/sms/studentController")
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private ClazzService clazzService;

    @GetMapping(value = "/getStudentByOpr/{pageNo}/{pageSize}")
    public Result pagination(@PathVariable Integer pageNo,@PathVariable Integer pageSize,Student student){
        Page page=new Page(pageNo,pageSize);
        Page list = studentService.pagination(page, student);
        return Result.ok(list);
    }

    @PostMapping(value = "/addOrUpdateStudent")
    public Result addOrUpdateStudent(@RequestBody Student student){
        student.setPassword(MD5.encrypt(student.getPassword()));
        boolean flag = studentService.saveOrUpdate(student);
        if (flag==false){
            return Result.fail("系统繁忙，请稍后重试");
        }
        return Result.ok();
    }

    @DeleteMapping(value = "/delStudentById")
    public Result delStudentById(@RequestBody List<Integer> ids){
        boolean flag = studentService.removeByIds(ids);
        if (flag){
            return Result.ok();
        }
        return Result.fail("系统繁忙，请稍后重试！");
    }

}
