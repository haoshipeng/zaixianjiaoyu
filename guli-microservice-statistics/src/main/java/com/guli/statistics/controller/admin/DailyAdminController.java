package com.guli.statistics.controller.admin;


import com.guli.common.vo.R;
import com.guli.statistics.service.DailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-09-10
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/statistics/daily")
@Api(description = "统计中心")
public class DailyAdminController {

    @Autowired
    private DailyService dailyService;

    @ApiOperation(value = "统计注册人数")
    @GetMapping("{day}")
    public R createStatisticsByDate(@PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }
}

