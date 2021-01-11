package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IDeptEvaluationDao extends BaseMapper<DeptEvaluation> {
    /**
     * 按工作年限获取
     * @param studentno
     * @param workYear
     * @return
     */
    Map<String, Object> getDeptEvaluationInfoByYear(@Param("studentno") int studentno, @Param("workYear") int workYear);

    /**
     * 更新部门评价
     * @param deptEvaluation 部门
     * @return 是否成功
     */
    boolean updateDeptEvaluation(DeptEvaluation deptEvaluation);
}
