package com.sm.cn.controller;

import com.sm.cn.common.http.entity.ResponseBean;
import com.sm.cn.common.http.upload.FileUtils;
import com.sm.cn.common.http.upload.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Part;

@RestController
public class UploadController {

    @Value("${bucketRegion}")
    private String bucketRegion;

    @Autowired
    private UploadUtils uploadUtils;

    @PostMapping("upload")
    public ResponseBean upload(@RequestPart Part avater) throws Exception{
        String fileName = FileUtils.getFileName(avater);
        uploadUtils.fileUpload(fileName,avater.getInputStream());
        return ResponseBean.success(bucketRegion + fileName);


    }
}
