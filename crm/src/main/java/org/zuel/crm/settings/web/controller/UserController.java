package org.zuel.crm.settings.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zuel.crm.commons.constants.Constants;
import org.zuel.crm.commons.domain.ReturnObject;
import org.zuel.crm.commons.utils.DateUtils;
import org.zuel.crm.settings.domain.User;
import org.zuel.crm.settings.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录界面
     * @return
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }



    /**
     * 登录账号
     * @param loginAct
     * @param loginPwd
     * @param isRemPwd
     * @return
     */
    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response){
        //封装
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        //返回给前端的数据封装
        ReturnObject returnObject = new ReturnObject();

        //查询
        User user = userService.queryUserByLoginActAndPwd(map);

        if(user == null){
            returnObject.setCode("0");
            returnObject.setMessage("用户名或者密码错误");
        }else {
            //判断账号的状态，ip等
            if (DateUtils.formateDateTime(new Date()).compareTo(user.getExpireTime())>0){
                //账号过期
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号过期");
            }else if("0".equals(user.getLockState())){
                //账号锁定
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("用户名状态锁定");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                //未授权的ip
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            }else {
                //登录成功
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                //把user信息保存到session
                request.getSession().setAttribute(Constants.SESSION_USER,user);
                //记住密码,如果需要记住密码，则使用cookie,  这里保存密码的cookie应该使用密文，
                if("true".equals(isRemPwd)){
                    Cookie loginActCookie = new Cookie("loginAct", user.getLoginAct());
                    Cookie loginPwdCookie = new Cookie("loginPwd", user.getLoginPwd());
                    //设置最大存贮时间
                    loginActCookie.setMaxAge(10*24*60*60);
                    loginPwdCookie.setMaxAge(10*24*60*60);
                    //返回Cookie
                    response.addCookie(loginActCookie);
                    response.addCookie(loginPwdCookie);
                }else{
                    //把没有过期的Cookie删除,将Cookie的生命周期置零,cookie的值任意，一般取1
                    Cookie loginActCookie = new Cookie("loginAct", "1");
                    Cookie loginPwdCookie = new Cookie("loginPwd", "1");
                    //设置最大存贮时间,为0 可以让浏览器删除
                    loginActCookie.setMaxAge(0);
                    loginPwdCookie.setMaxAge(0);
                    //返回Cookie
                    response.addCookie(loginActCookie);
                    response.addCookie(loginPwdCookie);
                }
            }
        }
        return returnObject;
    }


    /**
     * 安全退出
     * @return
     */
    @GetMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response, HttpSession session){
        //销毁cookie
        Cookie loginActCookie = new Cookie("loginAct", "");
        Cookie loginPwdCookie = new Cookie("loginPwd", "");
        //设置最大存贮时间,为0 可以让浏览器删除
        loginActCookie.setMaxAge(0);
        loginPwdCookie.setMaxAge(0);
        //返回Cookie
        response.addCookie(loginActCookie);
        response.addCookie(loginPwdCookie);
        //销毁session
        session.invalidate();
        return "redirect:/";
    }
}
