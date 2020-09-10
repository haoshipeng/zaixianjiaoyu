package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.service.ChapterService;
import com.guli.edu.service.CourseService;
import com.guli.edu.vo.ChapterVo;
import com.guli.edu.vo.CourseWebVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-06-05
 */
@RestController
@RequestMapping("/edu/course")
@Api(description = "课程详情")




public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ChapterService chapterService;

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.selectCourseWebVoById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);

        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }

}

