package com.sm.cn.common.http.upload;

import org.springframework.util.StringUtils;

import javax.servlet.http.Part;
import java.util.UUID;

public class FileUtils {
    public static String getFileName(Part part){
        //获取文件上传的文件名
        String submittedFileName = part.getSubmittedFileName();
        //获取上传文件的后缀名
        String ext = StringUtils.getFilenameExtension(submittedFileName);
        //重新编写文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");

        //目录打散
        //第一步：获取新名称的hashcode
        int code = fileName.hashCode();
        //第二步：获取后一位做为第一层目录
        String dir1 = Integer.toHexString(code & 0xf);
        //获取第二层的目录
        String dir2 = Integer.toHexString((code >> 4) & 0xf);
        String realPath=dir1 + "/" + dir2 +"/"+fileName + "." + ext;
        return realPath;

    }
}
