package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IDeptEvaluationDao extends BaseMapper<DeptEvaluation> {
    Map<String, Object> getDeptEvaluationInfoByYear(@Param("studentno") int studentno, @Param("workYear") int workYear);
}
