package com.example.springboot_coolshark.mapper;

import com.example.springboot_coolshark.pojo.vo.UserVO;

public interface UserMapper {
    UserVO selectByUsername(String username);
}
