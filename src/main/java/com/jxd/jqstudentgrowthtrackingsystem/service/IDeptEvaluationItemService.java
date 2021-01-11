package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluationItems;

public interface IDeptEvaluationItemService extends IService<DeptEvaluationItems> {

    /**
     * 更新部门评价分项
     * @param deptEvaluationItems 部门评价分项
     * @return 是否成功
     */
    boolean updateDeptEvaluationItem(DeptEvaluationItems deptEvaluationItems);
}
