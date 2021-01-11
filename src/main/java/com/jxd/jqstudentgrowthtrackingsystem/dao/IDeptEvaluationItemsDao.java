package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluationItems;

public interface IDeptEvaluationItemsDao extends BaseMapper<DeptEvaluationItems> {
    /**
     * 更新部门评价分项
     * @param deptEvaluationItems 部门评价分项
     * @return 是否成功
     */
    boolean updateDeptEvaluationItem(DeptEvaluationItems deptEvaluationItems);
}
