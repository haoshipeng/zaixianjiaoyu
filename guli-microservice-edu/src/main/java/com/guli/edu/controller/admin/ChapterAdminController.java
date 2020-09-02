package com.guli.edu.controller.admin;

import com.guli.common.vo.R;
import com.guli.edu.entity.Chapter;
import com.guli.edu.mapper.ChapterMapper;
import com.guli.edu.service.ChapterService;
import com.guli.edu.vo.ChapterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "章节管理")
@CrossOrigin
@RequestMapping("/admin/edu/chapter")
@Slf4j
public class ChapterAdminController {

    @Autowired
    private ChapterService chapterService;

    @ApiOperation(value = "新增章节")
    @PostMapping
    public R save(
            @ApiParam(name = "chapter",value = "章节对象",required = true)
            @RequestBody Chapter chapter){

        boolean result = chapterService.save(chapter);
        if(result){
            return R.ok().message("章节保存成功");
        }else{
            return R.ok().message("章节保存失败");
        }
    }


    @ApiOperation(value = "根据id修改章节")
    @PutMapping("{id}")
    public R update(
            @ApiParam(name = "chapter",value = "章节对象",required = true)
            @RequestBody Chapter chapter,
            @ApiParam(name = "id",value = "章节id",required = true)
            @PathVariable String id){

        boolean result = chapterService.updateById(chapter);
        if(result){
            return R.ok().message("章节修改成功");
        }else{
            return R.ok().message("章节修改失败");
        }
    }

    @ApiOperation(value = "根据id查询章节")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id",value = "章节id",required = true)
            @PathVariable String id){

        Chapter chapter = chapterService.getById(id);

            return R.ok().data("items",chapter);
    }

    @ApiOperation(value = "根据id删除章节")
    @DeleteMapping("{id}")
    public R deleteById(
            @ApiParam(name = "id",value = "章节id",required = true)
            @PathVariable String id){

        boolean result = chapterService.removeChapterById(id);
        if(result){
            return R.ok().message("章节删除成功");
        }else{
            return R.ok().message("章节删除失败");
        }
    }

    @ApiOperation(value = "根据id查询章节列表")
    @GetMapping("nested-list/{courseId}")
    public R nestedListByCourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return R.ok().data("items", chapterVoList);
    }
}
