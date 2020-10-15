package com.sm.cn.common.http.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
@Component
@PropertySource("classpath:aliyun.properties")
public class UploadUtils {
    @Value("${endpoint}")
    private  String endpoint;
    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessKeySecret}")
    private String accessKeySecret;
    @Value("${bucket}")
    private String bucket;
    public void fileUpload(String fileName, InputStream inputStream){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传网络流。
        ossClient.putObject(bucket, fileName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
