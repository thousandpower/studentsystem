package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IGradeDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Grade;
import com.jxd.jqstudentgrowthtrackingsystem.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName GradeServiceImpl
 * @Description TODO
 * @Author liutong
 * @Date 2021/1/4 9:00
 * @Version 1.0
 */
@Service
public class GradeServiceImpl extends ServiceImpl<IGradeDao, Grade> implements IGradeService {
    @Autowired
    private IGradeDao gradeDao;

    @Override
    public List<Grade> selectAllGrade() {
        return gradeDao.selectAllGrade();
    }
}
