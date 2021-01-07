package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IDeptEvaluationDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluation;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName DeptEvaluationServiceImpl
 * @Description TODO
 * @Author liutong
 * @Date 2021/1/4 8:56
 * @Version 1.0
 */
@Service
public class DeptEvaluationServiceImpl extends ServiceImpl<IDeptEvaluationDao, DeptEvaluation> implements IDeptEvaluationService {
 @Autowired
    private IDeptEvaluationDao deptEvaluationDao;

    @Override
    public Map<String, Object> getDeptEvaluationInfoByYear(int studentno, int workYear) {
        return deptEvaluationDao.getDeptEvaluationInfoByYear(studentno,workYear);
    }
}
