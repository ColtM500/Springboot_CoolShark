package com.example.springboot_coolshark.controller;

import com.example.springboot_coolshark.mapper.BannerMapper;
import com.example.springboot_coolshark.pojo.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/banner/")
public class BannerController {
    @Value("${imagesPath}")
    private String dirPath;

    @Autowired(required = false)
    BannerMapper mapper;

    @RequestMapping("select")
    public List<Banner> select(){
        return mapper.select();
    }

    @RequestMapping("delete")
    public void deleteById(int id){
        //通过轮播图id查询到url 删除图片文件
        String url = mapper.selectUrlById(id);
        new File(dirPath+url).delete();
        //删除数据库中的数据
        mapper.deleteById(id);
    }

    @RequestMapping("insert")
    public void insert(String url){
        System.out.println("url = " + url);
        mapper.insert(url);
    }

    @RequestMapping("selectById")
    public Banner selectById(int id){
        return mapper.selectById(id);
    }

    @RequestMapping("update")
    public void update(@RequestBody Banner banner){
        System.out.println("banner = " + banner);
        //通过轮播图id查询到原来的url 找到对应的文件并删除
        String url = mapper.selectUrlById(banner.getId());
        new File(dirPath+url).delete();
        mapper.update(banner);
    }

}
