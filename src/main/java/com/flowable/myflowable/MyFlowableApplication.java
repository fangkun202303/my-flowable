package com.flowable.myflowable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.flowable.myflowable.dao")
public class MyFlowableApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFlowableApplication.class, args);
    }

}
