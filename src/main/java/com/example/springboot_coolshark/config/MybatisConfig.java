package com.example.springboot_coolshark.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.springboot_coolshark.mapper")
public class MybatisConfig {
}
