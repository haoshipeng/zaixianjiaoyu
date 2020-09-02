package com.guli.edu.controller.admin;

import com.guli.common.vo.R;
import com.guli.edu.form.CourseInfoForm;
import com.guli.edu.service.CourseService;
import com.guli.edu.vo.CoursePublishVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api(description = "课程管理")
@RequestMapping("/admin/edu/course")
@Slf4j
public class CourseAdminController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "新增课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm){

            String courseId = courseService.saveCourseInfo(courseInfoForm);

        return R.ok().data("courseId", courseId);
    }

    @ApiOperation("根据id查询课程信息")
    @GetMapping("course-info/{id}")
    public R getById(
            @ApiParam(name = "id",value = "课程id",required = true)
            @PathVariable String id){

        CourseInfoForm courseInfoForm = courseService.getCourseInfoById(id);
        return R.ok().data("item",courseInfoForm);
    }


    @ApiOperation("更新课程信息")
    @PostMapping("update-course-info/{id}")
    public R updateCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm,

            @ApiParam(name = "id",value = "课程id",required = true)
            @PathVariable String id){

        courseService.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        courseService.removeCourseById(id);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        CoursePublishVo courseInfoForm = courseService.getCoursePublishVoById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "根据ID发布课程")
    @GetMapping("publish-course/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        courseService.publishCourseById(id);
        return R.ok();
    }

}
