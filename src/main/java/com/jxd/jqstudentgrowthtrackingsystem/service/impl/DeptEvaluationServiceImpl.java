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

    /**
     * 按工作年限获取
     * @param studentno
     * @param workYear
     * @return
     */
    @Override
    public Map<String, Object> getDeptEvaluationInfoByYear(int studentno, int workYear) {
        return deptEvaluationDao.getDeptEvaluationInfoByYear(studentno,workYear);
    }

    /**
     * 更新部门评价
     * @param deptEvaluation 部门
     * @return 是否成功
     */
    @Override
    public boolean updateDeptEvaluation(DeptEvaluation deptEvaluation) {
        return deptEvaluationDao.updateDeptEvaluation(deptEvaluation);
    }
}
