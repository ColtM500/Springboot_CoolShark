package com.example.springboot_coolshark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//Servlet组件扫描注解 使用过滤器必须添加此注解
@ServletComponentScan
@SpringBootApplication
public class SpringbootCoolSharkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCoolSharkApplication.class, args);
    }

}
