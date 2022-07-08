package com.cn.campusmanagement.abnormal;

import com.cn.campusmanagement.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result dealWithException(){
        return Result.fail().message("系统繁忙，请稍后重试！");
    }

}
