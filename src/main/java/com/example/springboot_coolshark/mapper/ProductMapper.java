package com.example.springboot_coolshark.mapper;

import com.example.springboot_coolshark.pojo.dto.ProductUpdateDTO;
import com.example.springboot_coolshark.pojo.entity.Product;
import com.example.springboot_coolshark.pojo.vo.*;

import java.util.List;

public interface ProductMapper {
    void insert(Product product);

    List<ProductAdminVO> selectAdmin();

    String selectUrlById(int id);

    void deleteById(int id);

    List<ProductTopVO> selectForTop();

    List<ProductIndexVO> selectIndex();

    ProductUpdateVO selectUpdateById(int id);

    void update(Product product);

    ProductDetailVO selectDetailById(int id);

    void updateViewCountById(int id);

    List<ProductIndexVO> selectByCid(int id);

    List<ProductIndexVO> selectByWd(String wd);
}
