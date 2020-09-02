package com.guli.edu.service;

import com.guli.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.vo.SubjectVo2;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-06-05
 */
@Repository
public interface SubjectService extends IService<Subject> {

    List<String>  batchImport(MultipartFile file) throws Exception;

    List<SubjectVo2> nestedList();
}
