package com.guli.edu.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.TeacherQuery;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-06-05
 */
@RestController
@RequestMapping("/admin/edu/teacher")
@Api(description = "讲师管理")
@CrossOrigin //跨域
public class TeacherAdminController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("所有讲师")
    @GetMapping
    public R list(){

        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list).message("获取讲师列表成功");
    }

    @ApiOperation("根据id删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(value = "讲师id",name="id",required = true)
            @PathVariable String id){

        boolean result = teacherService.removeById(id);
        if(result){
            return R.ok().message("删除讲师成功");
        }else{
           return R.error().message("删除讲师失败");
        }

    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(value = "当前页码",name="page",required = true)
            @PathVariable  Long page,
            @ApiParam(value = "每页记录数",name="limit",required = true)
            @PathVariable Long limit,
            @ApiParam(value = "查询对象",name="teacherQuery",required = false)
            TeacherQuery teacherQuery){

        Page<Teacher> pageParam = new Page<>(page, limit);
        teacherService.pageQuery(pageParam,teacherQuery);

        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("rows",records).data("total",total);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){

        boolean result = teacherService.save(teacher);
        if(result){
            return R.ok().message("讲师添加成功");
        }else{
            return R.error().message("讲师添加失败");
        }
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){

        teacher.setId(id);
        boolean result = teacherService.updateById(teacher);
        if(result){
            return R.ok().message("讲师修改成功");
        }else{
            return R.error().message("讲师修改失败");
        }

    }

}

