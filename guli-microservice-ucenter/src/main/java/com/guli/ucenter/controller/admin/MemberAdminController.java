package com.guli.ucenter.controller.admin;


import com.guli.common.vo.R;
import com.guli.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-09-09
 */
@RestController
@RequestMapping("admin/ucenter/member")
@CrossOrigin
@Api(description = "会员中心")
public class MemberAdminController {

    @Autowired
    MemberService memberService;


    @GetMapping(value = "count_register/{day}")
    @ApiOperation("根据日期统计注册人数")
    public R RegisterCount(
            @ApiParam(name="day",value = "注冊日期")
            @PathVariable  String day){

        Integer count = memberService.selectRegisterByDay(day);

        return R.ok().data("count",count);
    }
}

