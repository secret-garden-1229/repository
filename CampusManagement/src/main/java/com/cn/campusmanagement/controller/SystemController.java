package com.cn.campusmanagement.controller;

import com.cn.campusmanagement.domain.Admin;
import com.cn.campusmanagement.domain.LoginForm;
import com.cn.campusmanagement.domain.Student;
import com.cn.campusmanagement.domain.Teacher;
import com.cn.campusmanagement.service.AdminService;
import com.cn.campusmanagement.service.StudentService;
import com.cn.campusmanagement.service.TeacherService;
import com.cn.campusmanagement.utils.*;
import com.sun.deploy.net.HttpResponse;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/sms/system")
public class SystemController {

    @Resource
    private AdminService adminService;

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    /*
    * 生成验证码
    * */
    @GetMapping(value = "/getVerifiCodeImage")
    public void getVerificationCode(HttpServletResponse response, HttpSession session){
        BufferedImage codeImge = CreateVerifiCodeImage.getVerifiCodeImage();
        String code = new String(CreateVerifiCodeImage.getVerifiCode());
        session.setAttribute("code",code);
        try {
            ImageIO.write(codeImge,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录共能
     * */
    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginForm loginForm,HttpSession session){
        String verifyCode= (String) session.getAttribute("code");
        if (verifyCode.equals("")||null==verifyCode){
           return  Result.fail().message("验证码有误请重试");
        }

        if (!verifyCode.equals(loginForm.getVerifiCode())){
            return  Result.fail().message("验证码有误请重试");
        }
        Map map=new HashMap();
        switch (loginForm.getUserType()){
            case 1:
                try {
                    Admin admin = adminService.login(loginForm.getUsername(), loginForm.getPassword());
                    if (null!=admin){
                        map.put("token",JwtHelper.createToken(admin.getId().longValue(),1));
                    }else {
                        return Result.fail().message("用户名或者密码错误！");
                    }
                    return Result.ok(map);
                } catch (Exception e) {
                    e.printStackTrace();
                    return  Result.fail().message("用户名或者密码错误！");
                }
            case 2:

                try {
                    Student student = studentService.login(loginForm.getUsername(), loginForm.getPassword());
                    if (null!=student){
                        map.put("token",JwtHelper.createToken(student.getId().longValue(),2));
                    }else {
                        return Result.fail().message("用户名或者密码错误！");
                    }
                    return Result.ok(map);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.fail().message("用户名或者密码错误！");
                }

            case 3:

                try {
                    Teacher teacher = teacherService.login(loginForm.getUsername(), loginForm.getPassword());
                    if (null!=teacher){
                        map.put("token",JwtHelper.createToken(teacher.getId().longValue(),3));
                    }else {
                        return Result.fail().message("用户名或者密码错误！");
                    }
                    return Result.ok(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }


        }

        return Result.fail().message("查无此用户！");
    }


    @GetMapping(value = "/getInfo")
    public Result getInfo( @RequestHeader("token") String token){
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration){
            return Result.build(null,ResultCodeEnum.TOKEN_ERROR);
        }

        Long id = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        Map map=new HashMap();
        switch (userType){
            case 1:
                Admin admin = adminService.selectAdminByID(id);
                map.put("user",admin);
                map.put("userType",1);
                break;
            case 2:
                Student student = studentService.selectStudentById(id);
                map.put("user",student);
                map.put("userType",2);
                break;
            case 3:
                Teacher teacher = teacherService.selectTeacherId(id);
                map.put("user",teacher);
                map.put("userType",1);
                break;
        }
        return Result.ok(map);
    }

    @PostMapping(value = "/headerImgUpload")
    public Result headerImgUpload(@RequestPart("multipartFile") MultipartFile multipartFile, HttpServletRequest request){
        String fileName="";
        String uploadPath="";
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            path=path.concat("public/upload/");
            int index = multipartFile.getOriginalFilename().lastIndexOf(".");
            String suffix=multipartFile.getOriginalFilename().substring(index);
            fileName=UUID.randomUUID().toString().replace("-","").concat(suffix);
            path=path+fileName;
            multipartFile.transferTo(new File(path));
            uploadPath="upload/"+fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
        return Result.ok(uploadPath);
    }


    @PostMapping(value = "/updatePwd/{oldpwd}/{newpwd}")
    public Result updatePwd(@RequestHeader("token") String token,@PathVariable String oldpwd, @PathVariable String newpwd ){
        System.out.println(" =================oldpwd:"+oldpwd);
        oldpwd=MD5.encrypt(oldpwd);
        System.out.println("==================oldpwd:"+oldpwd);
        System.out.println("==================newpwd:"+newpwd);
        Long id = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        if (JwtHelper.isExpiration(token)){
            return Result.fail().message("token已过期，请重新登录！");
        }
        switch (userType){
            case 1:
                Admin admin = adminService.selectAdminByID(id);
                if (admin==null||!oldpwd.equals(admin.getPassword())){
                    System.out.println("1");
                    System.out.println(oldpwd.equals(admin.getPassword()));
                    return Result.fail().message("账号或密码错误！");
                }
                admin.setPassword(MD5.encrypt(newpwd));
                admin.setId(id.intValue());
                adminService.saveOrUpdate(admin);
                return Result.ok();
            case 2:
                Student student = studentService.selectStudentById(id);
                if (student==null||!oldpwd.equals(student.getPassword())){
                    System.out.println("2");
                    return Result.fail().message("账号或密码错误！");
                }
                student.setPassword(MD5.encrypt(newpwd));
                student.setId(id.intValue());
                studentService.saveOrUpdate(student);
                return Result.ok();
            case 3:
                Teacher teacher = teacherService.selectTeacherId(id);
                if (teacher==null||!oldpwd.equals(teacher.getPassword())){
                    System.out.println("3");
                    return Result.fail().message("账号或密码错误！");
                }
                teacher.setPassword(MD5.encrypt(newpwd));
                teacher.setId(id.intValue());
                teacherService.saveOrUpdate(teacher);
                return Result.ok();
        }
        return Result.fail().message("请确认用户是否存在！");
    }


}
