package com.cn.oa.business.controller;


import com.cn.oa.business.service.DepartService1;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


@RestController
public class DepartController {
    @Resource
    private DepartService1 departService1;


    /**
     * 根据id删除
     * */
    @GetMapping(value = "departController/deleteDepartById")
    public boolean deleteDepartById(@RequestParam("id") Integer id){
        Integer sum = departService1.deleteDepartByDid(id,0);
        if (sum<1){
            return false;
        }
        return true;
    }

    /**
     * 修改部门信息
     * */
    @GetMapping(value = "departController/updateDepartById")
    public boolean updateDepartById(@RequestParam("did") Integer did,@RequestParam(value = "dname") String dname,@RequestParam(value = "duty") String duty){
        Integer sun = departService1.updateDepartByDid(did, dname, duty);
        if (sun!=1){
            return false;
        }
        return true;
    }

    /**
     * 添加部门信息
     * */
    @PostMapping(value = "departController/addDepart")
    public boolean addDepart(@RequestParam(value = "dname")String  dname,@RequestParam(value = "duty")String duty){
        Integer sum = departService1.addSelective(dname,duty,new Date());
        if (sum!=1){
            return false;
        }
        return true;
    }

}
