package com.cn.oa.business.controller;

import com.cn.oa.business.service.DepartService1;
import com.cn.oa.business.service.EmployeeSerivce;
import com.cn.oa.constants.RoleConstant;
import com.cn.oa.domain.Depart;
import com.cn.oa.domain.Employee;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Resource
    private EmployeeSerivce employeeSerivce;

    @Resource
    private DepartService1 departService1;

/**
 * 查询
 * */
    @GetMapping(value = "userController/selectEmployee")
    public ModelAndView selectEmployee(@RequestParam Map<String,String> map, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "4")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        ModelAndView modelAndView=new ModelAndView();
        List<Employee> list = employeeSerivce.selectEmpoyee(map);
        PageInfo<Employee> page=new PageInfo<>(list);
        modelAndView.addObject("pages",page);
        modelAndView.setViewName("/user.jsp");
        return modelAndView;/*user.jsp*/
    }

    /**
     * 删除员工
     * */
    @GetMapping(value = "userController/deleteEmployeeById")
    public boolean deleteEmployeeById(@RequestParam(value = "eid") Integer eid,@RequestParam(value = "estatus") Integer estatus){
        Integer sun = employeeSerivce.deleteEmpoyeeById(eid, estatus);
        if (sun!=1){
            return false;
        }
        return true;
    }

    /**
     * 添加员工
     * */
    @PostMapping(value = "userController/addEmployee")
    public boolean addEmployee(@RequestBody Employee employee){
        employee.setEntrydate(new Date());
        employee.setSal(2000);
        employee.setEstatus(0);
        System.out.println(employee);
        Integer integer = employeeSerivce.addEmpoyee(employee);
        if (integer!=1) return false;
        return true;
    }

    /**
     * 查询所有的部门ID和部门名称
     * 查询所有的角色
     * */
    @GetMapping(value = "userController/selectAllDepartIDAndDname")
    public ModelAndView selectAllDepartIDAndDname(){
        ModelAndView modelAndView=new ModelAndView();
        List<Depart> departList = departService1.selectAllDepart();
        List<Employee> collect = employeeSerivce.selectPosition().stream().map(x->{
            Integer position = x.getPosition();
            String role = RoleConstant.getRole(String.valueOf(position));
            x.setRole(role);
            return x;
        }).collect(Collectors.toList());
        modelAndView.addObject("collect",collect);
        modelAndView.addObject("departList",departList);
        modelAndView.setViewName("/addUser.jsp");
        return modelAndView;
    }

    /**
     * 根据id查询
     * */
    @GetMapping(value = "userController/selectEmpoleeById")
    public ModelAndView selectEmpoleeById(String eid){
        ModelAndView modelAndView=new ModelAndView();
        Employee employee = employeeSerivce.selectByPrimaryKey(eid);
        String roles = RoleConstant.getRole(String.valueOf(employee.getPosition()));
        employee.setRole(roles);
        List<Depart> departList = departService1.selectAllDepart();
        List<Employee> collect = employeeSerivce.selectPosition().stream().map(x->{
            Integer position = x.getPosition();
            String role = RoleConstant.getRole(String.valueOf(position));
            x.setRole(role);
            return x;
        }).collect(Collectors.toList());
        modelAndView.addObject("collect",collect);
        modelAndView.addObject("departList",departList);
        modelAndView.addObject("employee",employee);
        modelAndView.setViewName("/editUser.jsp");
        return modelAndView;
    }
    /**
     * 修改
     * */
    @PostMapping(value = "userController/updateEmployee")
    public boolean updateEmployee(@RequestBody Employee employee){
        Integer integer = employeeSerivce.updateByPrimaryKeySelective(employee);
        if (integer!=1) return false;
        return true;
    }

}
