package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.entity.Subject;
import com.guli.edu.mapper.SubjectMapper;
import com.guli.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.utils.ExcelImportUtil;
import com.guli.edu.vo.SubjectVo2;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-06-05
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public List<String> batchImport(MultipartFile file) throws Exception {

        //定义错误信息列表
        List<String> errorMsg = new ArrayList<>();
        //获取文件上传流
        InputStream inputStream = file.getInputStream();
        //使用excel导入工具类
        ExcelImportUtil excelImportUtil = new ExcelImportUtil(inputStream);
        //获取sheet
        HSSFSheet sheet = excelImportUtil.getSheet();
        //获取行数
        int rowCont = sheet.getPhysicalNumberOfRows();
        if(rowCont <= 1){
            //如果只有标题则返回错误信息
            errorMsg.add("请填写数据");
        }

        String levelOneValue = "";
        for(int rowNum = 1;rowNum < rowCont; rowNum++){
            HSSFRow rowData = sheet.getRow(rowNum);
            if(rowData != null){
                //从excel获取一级分类的数据
                HSSFCell levelOneCell = rowData.getCell(0);
                if(levelOneCell != null){
                    levelOneValue = excelImportUtil.getCellValue(levelOneCell).trim();
                    if(StringUtils.isEmpty(levelOneValue)){
                        errorMsg.add("第" + rowNum + "行一级分类为空");
                        continue;
                    }
                }
                //判断数据库中一级分类是否存在
                Subject subject = this.getByTitle(levelOneValue);
                String parentid = "";
                if(subject == null){
                    //如果不存在则插入到数据库中
                    Subject subjectLevelOne = new Subject();
                    subjectLevelOne.setTitle(levelOneValue);
                    subjectLevelOne.setSort(rowNum);
                    baseMapper.insert(subjectLevelOne);
                    //获取id(二级分类的parentid)
                    parentid = subjectLevelOne.getId();
                }else{
                    parentid = subject.getId();
                }

            }

        }


        return errorMsg;
    }

    @Override
    public List<SubjectVo2> nestedList() {
        return baseMapper.selectNestedListByParentId("0");
    }

    /**
     * 判断一级分类是否重复
     * @param title
     * @return
     */
    private Subject getByTitle(String title){
        QueryWrapper<Subject> aqueryWrapper = new QueryWrapper<>();
        aqueryWrapper.eq("title",title);
        aqueryWrapper.eq("parent_id",0);
        return baseMapper.selectOne(aqueryWrapper);

    }
}
