package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluation;

import java.util.List;
import java.util.Map;

public interface IDeptEvaluationService extends IService<DeptEvaluation> {
    /**
     * 按工作年限获取
     * @param studentno
     * @param workYear
     * @return
     */
    Map<String, Object>  getDeptEvaluationInfoByYear(int studentno, int workYear);

}
