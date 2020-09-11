package com.guli.edu.client;

import com.guli.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient("GULI-OSS")
public interface OssClient {

    @DeleteMapping("/admin/oss/file/remove")
    public R removeFile(
            @RequestBody String url);
}
