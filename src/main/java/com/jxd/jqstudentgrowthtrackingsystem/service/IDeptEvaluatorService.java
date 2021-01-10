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
    /**
     * 分页模糊查询项目评价人信息
     *  fws
     * @param deptEvaluatorName 模糊查询姓名
     * @param limit             每页显示数量
     * @param page              页码
     * @return 分页查询项目评价人的信息
     */
    Map<String, Object> getDeptEvaluatorsByPage(String deptEvaluatorName, int limit, int page);
}
