package com.cn.oa.constants;

import java.util.HashMap;
import java.util.Map;

public class RoleConstant {

    private RoleConstant() {}

    private static class role{
        private  static  Map<String,String> map=new HashMap<>();

         static {
             role.map.put("0","总裁");
             role.map.put("1","经理");
             role.map.put("2","普通员工");
         }

    }

    public static String getRole(String key){
        return role.map.get(key);
    }


}
