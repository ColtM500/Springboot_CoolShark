package com.example.springboot_coolshark.mapper;

import com.example.springboot_coolshark.pojo.entity.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface CategoryMapper {

    List<Category> select();

    void deleteById(int id);

    void insert(String name);

    void update(Category category);
}
