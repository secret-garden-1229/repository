package com.cn.oa.setting.controller;

import com.cn.oa.Utils.FileUploadUtils;
import com.cn.oa.domain.Depart;
import com.cn.oa.domain.Employee;
import com.cn.oa.setting.service.DepartService;
import com.cn.oa.setting.service.LoginService;
import com.mysql.cj.PreparedQuery;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@RestController
public class SettingController {

    private volatile static Map<String,HttpSession> session=new HashMap<>();

    @Resource
    DepartService departService;

    @Resource
    private LoginService loginService;
    /**
     * 进入登录页面
     * */
   @GetMapping(value = "/")
    public ModelAndView toLogin(){
          ModelAndView modelAndView=new ModelAndView();
          modelAndView.setViewName("/login.jsp");
          return modelAndView;
    }

    @PostMapping(value = "setting/login")
    public boolean login( HttpSession sessions,@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value = "remember",defaultValue = "") String remember, HttpServletResponse response){
       Employee user = loginService.login(username, password);
        if (user==null){
            return false;
        }
        System.out.println("================="+sessions.getId());
        sessions.setAttribute("user",user);
        /*if (SettingController.session.size()==0){
            synchronized(SettingController.class){
                if (SettingController.session.size()==0){
                    SettingController.session.put("session",sessions);
                }
            }

        }*/

        if (remember!=""&&remember=="true"){
            Cookie cookie=new Cookie(user.getEname(),user.getEpass());
            cookie.setMaxAge(60*30);
            response.addCookie(cookie);
            return  true;
        }

        return true;
    }

    /**
     * 跳转到index.jsp
     * */
    @GetMapping("setting/toIndex")
    public ModelAndView toIndex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/index.jsp");
        return modelAndView;
    }

/**
 * 退出功能，销毁当前用户的session
 * */
    @GetMapping("loginOut")
    public void  loginOut(HttpServletRequest request,HttpServletResponse response,HttpSession sessions) throws ServletException, IOException {
            //立即销毁session
        System.out.println("================="+sessions.getId());
        if (sessions!=null){
            sessions.invalidate();
        }
        /*if (SettingController.session.size()==1){
            HttpSession sessiones = SettingController.session.get("session");
            synchronized(SettingController.class){
                if (sessiones!=null){
                    sessiones.invalidate();
                    SettingController.session.remove("session");
                }
            }
        }*/
        //销毁cookie
        Cookie cookie=new Cookie("0","0");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    /**
     * 修改密码
     * */
    @GetMapping("setting/updatePassword")
    public boolean updatePassword(HttpServletResponse response,@RequestParam(value = "oldPassword") String oldPassword,@RequestParam(value = "password",required = false) String password,HttpSession session) throws ServletException, IOException {
        Employee user = (Employee) session.getAttribute("user");
        Integer did = user.getDid();
        if (oldPassword.equals(password)){
            if (!oldPassword.equals("")&&!password.equals("")){
                System.out.println("2");
                loginService.updatePassword(password,did);
                return true;
            }

        }
        return false;
    }

    /**
     * 查询所有有效部门
     * */

    @GetMapping(value = "setting/selectEfficientDepartment")
    public ModelAndView selectEfficientDepartment(@RequestParam(value = "dstatus",required = false) Integer dstatus){
        List<Depart> depart = departService.selectAllByDstatus(dstatus);
        ModelAndView model=new ModelAndView();
        model.addObject("departList",depart);
        model.setViewName("/depart.jsp");
        return model;
    }

    /**
     * 上传文件
     * */
    @PostMapping(value = "/upload_logo.do")
    public String updateImg(@RequestParam(value = "photo") MultipartFile  file/*,HttpServletRequest request*/){
        System.out.println("=============================");
       /* MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> files = multipartRequest.getFileNames();
        while (files.hasNext()){
            String next = files.next();
            System.out.println(next);
        }*/
        System.out.println(file==null);
        System.out.println(file);
        int i=0;
        String path="";
        try {
             path = new FileUploadUtils(file).saveFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            i++;
        }
        System.out.println(path);
        if (i>0) return "失败" ;
        return path;
    }


}
