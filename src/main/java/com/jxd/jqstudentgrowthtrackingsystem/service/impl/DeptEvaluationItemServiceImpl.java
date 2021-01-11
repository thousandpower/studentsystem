package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IDeptEvaluationItemsDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluation;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluationItems;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptEvaluationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName DeptEvaluationItemServiceImpl
 * @Description TODO
 * @Author liutong
 * @Date 2021/1/4 8:58
 * @Version 1.0
 */
@Service
public class DeptEvaluationItemServiceImpl extends ServiceImpl<IDeptEvaluationItemsDao, DeptEvaluationItems> implements IDeptEvaluationItemService {
    @Autowired
    private IDeptEvaluationItemsDao deptEvaluationItemsDao;
    /**
     * 更新部门评价分项
     * @param deptEvaluationItems 部门评价分项
     * @return 是否成功
     */
    @Override
    public boolean updateDeptEvaluationItem(DeptEvaluationItems deptEvaluationItems) {
        return deptEvaluationItemsDao.updateDeptEvaluationItem(deptEvaluationItems) ;
    }

}
