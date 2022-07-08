package com.cn.campusmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"com.cn.campusmanagement.mapper"})
@EnableTransactionManagement
public class CampusManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusManagementApplication.class, args);
    }

}
