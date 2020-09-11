package com.guli.statistics.client.exception;

import com.guli.common.vo.R;
import com.guli.statistics.client.UcenterClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UcenterClientExceptionHandlerService implements UcenterClient{


    @Override
    public R RegisterCount(String day) {
        log.error("远程服务调用失败，熔断处理");
        return R.ok().data("count", 0);
    }
}
