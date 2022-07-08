package com.cn.campusmanagement;

import com.cn.campusmanagement.utils.Result;
import com.cn.campusmanagement.utils.ResultCodeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CampusManagementApplicationTests {

    @Test
    void contextLoads() {
        ResultCodeEnum success = ResultCodeEnum.SUCCESS;
        System.out.println(success.getCode());
        System.out.println(success.getMessage());
    }


}
