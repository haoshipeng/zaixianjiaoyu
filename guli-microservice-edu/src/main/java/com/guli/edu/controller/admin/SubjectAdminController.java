package com.guli.edu.controller.admin;

import com.guli.common.util.ExceptionUtils;
import com.guli.common.vo.R;
import com.guli.edu.service.SubjectService;
import com.guli.edu.utils.ExcelImportUtil;
import com.guli.edu.vo.SubjectVo2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/edu/subject")
@Api(description = "课程分类管理")
@CrossOrigin //跨域
public class SubjectAdminController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("所有讲师")
    @PostMapping
    public R batchImport(
            @ApiParam(value = "讲师id",name="id",required = true)
            @RequestParam("file") MultipartFile file) throws Exception {


            List<String> errorMsg = subjectService.batchImport(file);
            if(errorMsg.size() == 0){
                return R.ok().message("批量导入成功");
            }else{
                return R.ok().message("部分数据批量导入失败");
            }
    }

    @ApiOperation(value = "获取课程的嵌套数据列表")
    @GetMapping("lis2")
    public R nestedList(){

        List<SubjectVo2> subjectVo2List = subjectService.nestedList();
        return R.ok().data("items", subjectVo2List);
    }
}
