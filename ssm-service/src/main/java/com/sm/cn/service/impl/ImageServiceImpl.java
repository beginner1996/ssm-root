package com.sm.cn.service.impl;

import com.sm.cn.entity.Image;
import com.sm.cn.mapper.ImageMapper;
import com.sm.cn.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageMapper imageMapper;
    @Override
    public List<Image> findAll() {
        return imageMapper.findAll();
    }
}
