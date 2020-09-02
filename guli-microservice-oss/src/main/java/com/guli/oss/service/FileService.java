package com.guli.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @file 前端传过来的文件
 * @fileHost 文件上传的具体路径
 */
public interface FileService {

    String upload(MultipartFile file,String fileHost) throws IOException;
}
