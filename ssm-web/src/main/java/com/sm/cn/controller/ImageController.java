package com.sm.cn.controller;

import com.sm.cn.entity.Image;
import com.sm.cn.service.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ImageController {

    @Resource
    private ImageService imageService;


    @GetMapping("find")
    public List<Image> findAll(){
        List<Image> all = imageService.findAll();
        return all;

    }
}
