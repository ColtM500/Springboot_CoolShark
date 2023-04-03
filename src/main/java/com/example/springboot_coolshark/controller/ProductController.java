package com.example.springboot_coolshark.controller;

import com.example.springboot_coolshark.mapper.ProductMapper;
import com.example.springboot_coolshark.pojo.dto.ProductDTO;
import com.example.springboot_coolshark.pojo.dto.ProductUpdateDTO;
import com.example.springboot_coolshark.pojo.entity.Product;
import com.example.springboot_coolshark.pojo.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product/")
public class ProductController {
    @Value("${imagesPath}")
    private String dirPath;

    @Autowired(required = false)
    ProductMapper mapper;

    @RequestMapping("insert")
    public void insert(@RequestBody ProductDTO productDTO) {
        System.out.println("productDTO = " + productDTO);
        Product p = new Product();
        BeanUtils.copyProperties(productDTO, p);
        p.setCreated(new Date());
        mapper.insert(p);
    }

    @RequestMapping("selectAdmin")
    public List<ProductAdminVO> select() {
        return mapper.selectAdmin();
    }

    @RequestMapping("delete")
    public void delete(int id) {
        //先得到图片路径 然后删除图片
        String url = mapper.selectUrlById(id);
        new File(dirPath + url).delete();
        //删除数据库图片
        mapper.deleteById(id);
    }

    @RequestMapping("selectUpdateById")
    public ProductUpdateVO selectUpdateById(int id) {
        return mapper.selectUpdateById(id);
    }

    @RequestMapping("update")
    public void update(@RequestBody ProductUpdateDTO productUpdateDTO) {
        System.out.println("productUpdateDTO = " + productUpdateDTO);
        //得到原来的图片路径
        String url = mapper.selectUrlById(productUpdateDTO.getId());
        //判断如果新路径和原来路径不相等则删除原来路径对应的图片
        if (!url.equals(productUpdateDTO.getUrl())) {
            new File(dirPath + url).delete();
        }
        Product product = new Product();
        BeanUtils.copyProperties(productUpdateDTO, product);

        mapper.update(product);
    }


    @RequestMapping("selectForTop")
    public List<ProductTopVO> selectForTop() {
        return mapper.selectForTop();
    }

    @RequestMapping("selectIndex")
    public List<ProductIndexVO> selectIndex() {
        return mapper.selectIndex();
    }

    @RequestMapping("selectDetailById")
    public ProductDetailVO selectDetailById(int id, HttpSession session) {
        //第一次从Session里面通过id获取时是获取不到内容的
        String info = (String) session.getAttribute("view" + id);
        if (info == null) {//info为null代表之前没浏览过
            //让商品的浏览量+1
            mapper.updateViewCountById(id);
            //把浏览过的id保存到session里面去
            session.setAttribute("view" + id, "visited");
        }
        return mapper.selectDetailById(id);
    }

    @RequestMapping("selectByCid")
    public List<ProductIndexVO> selectByCid(int id){
        return mapper.selectByCid(id);
    }

    @RequestMapping("selectByWd")
    public List<ProductIndexVO> selectByWd(String wd){
        return mapper.selectByWd(wd);
    }
}
