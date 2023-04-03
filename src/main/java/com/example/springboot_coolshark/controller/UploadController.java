package com.example.springboot_coolshark.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class UploadController {
    //@Value的作用是给变量赋值 ${xxx}写法会去application.properties里面找到对应的配置信息
    @Value("${imagesPath}")
    private String dirPath;

    @RequestMapping("/upload")
    public String upload(MultipartFile picFile) throws IOException {
        String fileName= picFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID()+suffix;
//        //得到保存图片的路径
//        String dirPath = "G:\\files\\CoolShark";
        String datePath = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        File dirFile = new File(dirPath+datePath);
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        picFile.transferTo(new File(dirPath+datePath+fileName));
        return datePath+fileName;
    }

    @RequestMapping("/remove")
    public void remove(String url){
        new File(dirPath+url).delete();
    }
}
