package com.cn.campusmanagement.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.campusmanagement.domain.Grade;
import com.cn.campusmanagement.service.GradeService;
import com.cn.campusmanagement.utils.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping(value = "/sms/gradeController")
@RestController
public class GradeController {

    @Resource
    private GradeService gradeService;

    @GetMapping(value = "/getGrades/{pageNo}/{pageSize}")
    public Result pagination(@PathVariable Integer pageNo, @PathVariable Integer pageSize, String gradeName){
        Page page=new Page(pageNo,pageSize);
        Grade grade=new Grade();
        grade.setName(gradeName);
        Page list = gradeService.pagination(page, grade);
        return Result.ok(list);
    }

    @DeleteMapping(value = "/deleteGrade")
    @Transactional
    public Result deleteGradeservice(@RequestBody List<Integer> ids){
        boolean flag = gradeService.removeByIds(ids);
       if (flag){
           return Result.ok();
       }else {
           return Result.fail().message("删除失败！请稍后再试");
       }
    }

    @PostMapping(value = "/saveOrUpdateGrade")
    @Transactional
    public Result saveOrUpdateGrade(@RequestBody Grade grade){
       gradeService.saveOrUpdate(grade);
        return Result.ok();

    }

    @GetMapping(value = "/getGrades")
    public Result getGrades(){
        return Result.ok(gradeService.getAllGrade());
    }


}
