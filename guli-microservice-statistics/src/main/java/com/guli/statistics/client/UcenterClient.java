package com.guli.statistics.client;

import com.guli.common.vo.R;
import com.guli.statistics.client.exception.UcenterClientExceptionHandlerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(value = "GULI-UCENTER",fallback=UcenterClientExceptionHandlerService.class)
public interface UcenterClient {
    /**
     * 注意：一定要写成 @PathVariable("day")，圆括号中的"day"不能少
     * @param day
     * @return
     */

    @GetMapping(value = "/admin/ucenter/member/count_register/{day}")
    public R RegisterCount(
            @PathVariable("day") String day);
}
