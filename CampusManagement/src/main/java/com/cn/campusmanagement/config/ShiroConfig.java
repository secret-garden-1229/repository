package com.cn.campusmanagement.config;


import com.cn.campusmanagement.golbalRealm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //配置Shiro的安全管理器
    @Bean
    public DefaultWebSecurityManager securityManager(Realm myRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //设置一个Realm，这个Realm是最终用于完成我们的认证号和授权操作的具体对象
        securityManager.setRealm(myRealm);
        return securityManager;
    }


    //配置一个自定义的Realm的bean，最终将使用这个bean返回的对象来完成我们的认证和授权
    @Bean
    public Realm myRealm(){
        MyRealm realm=new MyRealm();
        return realm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        //创建Shiro的拦截的拦截器 ，用于拦截我们的用户请求
        ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
        //设置Shiro的安全管理，设置管理的同时也会指定某个Realm 用来完成我们权限分配
        shiroFilter.setSecurityManager(securityManager);
        //用于设置一个登录的请求地址，这个地址可以是一个html或jsp的访问路径，也可以是一个控制器的路径
        //作用是用于通知Shiro我们可以使用这里路径转向到登录页面，但Shiro判断到我们当前的用户没有登录时就会自动转换到这个路径
        //要求用户完成成功
        shiroFilter.setLoginUrl("/");
        //登录成功后转向页面，由于用户的登录后期需要交给Shiro完成，因此就需要通知Shiro登录成功之后返回到那个位置
        shiroFilter.setSuccessUrl("/success");
        //用于指定没有权限的页面，当用户访问某个功能是如果Shiro判断这个用户没有对应的操作权限，那么Shiro就会将请求
        //转向到这个位置，用于提示用户没有操作权限
        shiroFilter.setUnauthorizedUrl("/noPermission");
        //定义一个Map集合，这个Map集合中存放的数据全部都是规则，用于设置通知Shiro什么样的请求可以访问什么样的请求不可以访问
        Map<String,String> map=new LinkedHashMap<String,String>();
        //  /login 表示某个请求的名字    anon 表示可以使用游客什么进行登录（这个请求不需要登录）
        map.put("/login","anon");
        //我们可以在这里配置所有的权限规则这列数据真正是需要从数据库中读取出来
        //或者在控制器中添加Shiro的注解
        //  /admin/**  表示一个请求名字的通配， 以admin开头的任意子孙路径下的所有请求
        //  authc 表示这个请求需要进行认证（登录），只有认证（登录）通过才能访问
        // 注意： ** 表示任意子孙路径
        //       *  表示任意的一个路径
        //       ? 表示 任意的一个字符
       /* map.put("/admin/**","authc");
        map.put("/sms/system/login","anon");*/
        //表示所有的请求路径全部都需要被拦截登录，这个必须必须写在Map集合的最后面,这个选项是可选的
        //如果没有指定/** 那么如果某个请求不符合上面的拦截规则Shiro将方行这个请求
        /*map.put("/**","authc");*/
        shiroFilter.setFilterChainDefinitionMap(map);
        return shiroFilter;
    }


    /**
     * 开启Shiro的注解例如（  @RequiresRoles @RequiresUser @RequiresPermissions）
     * 需要借助SpringAOP来扫描这些注解
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 开启AOP的注解支持
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }




}
