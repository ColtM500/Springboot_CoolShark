package com.example.springboot_coolshark.mapper;

import com.example.springboot_coolshark.pojo.entity.Banner;

import java.util.List;

public interface BannerMapper {
    List<Banner> select();

    void deleteById(int id);
    void insert(String url);

    Banner selectById(int id);

    String selectUrlById(int id);

    void update(Banner banner);
}
