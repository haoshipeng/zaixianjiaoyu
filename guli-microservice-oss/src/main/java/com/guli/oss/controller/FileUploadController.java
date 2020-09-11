package com.guli.oss.controller;

import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.vo.R;
import com.guli.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(description="阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/oss/file")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file", value = "文件")
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "fileHost") String fileHost) throws IOException {

        String uploadUrl = null;
        uploadUrl = fileService.upload(file, fileHost);
        //返回r对象
        return R.ok().message("文件上传成功").data("url", uploadUrl);
    }

    @ApiOperation(value = "文件删除")
    @DeleteMapping("remove")
    public R removeFile(
            @ApiParam(name = "url", value = "要删除的文件路径", required = true)
            @RequestBody String url) {

//        try {
            fileService.removeFile(url);
            return R.ok().message("文件刪除成功");
//        } catch (Exception e) {
//            log.error(ExceptionUtils.getMessage(e));
//            throw new GuliException(ResultCodeEnum.FILE_DELETE_ERROR);
//        }
    }
}
