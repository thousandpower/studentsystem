package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluator;

import java.util.Map;

/**
 * @ClassName: IDeptEvaluatorService
 * @Author: fws
 * @Description:
 * @Date: 2021/1/3 16:43
 */
public interface IDeptEvaluatorService extends IService<DeptEvaluator> {
    Map<String, Object> getDeptEvaluatorsByPage(String deptEvaluatorName, int limit, int page);
}
