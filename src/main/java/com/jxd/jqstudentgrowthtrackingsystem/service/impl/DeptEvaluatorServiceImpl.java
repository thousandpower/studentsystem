package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IDeptEvaluatorDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluator;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptEvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DeptEvaluatorServiceImpl
 * @Author: fws
 * @Description:
 * @Date: 2021/1/3 16:44
 */
@Service
public class DeptEvaluatorServiceImpl  extends ServiceImpl<IDeptEvaluatorDao, DeptEvaluator> implements IDeptEvaluatorService {
    @Autowired
    private IDeptEvaluatorDao deptEvaluatorDao;

    /**
     * 分页模糊查询项目评价人信息
     * @param deptEvaluatorName 模糊查询姓名
     * @param limit 每页显示数量
     * @param page  页码
     * @return 分页查询项目评价人的信息
     */
    @Override
    public Map<String, Object>  getDeptEvaluatorsByPage(String deptEvaluatorName, int limit, int page) {
        //构造分页对象
        Page<Map<String, Object>> pages = new Page<>(page, limit);
        Map<String, Object> map = new HashMap<>();
        //调用dao层获取数据
        IPage<Map<String, Object>> result =deptEvaluatorDao.getDeptEvaluatorsByPage(pages, deptEvaluatorName);
        //查询出的员工信息 （分页）
        map.put("deptEvaluators",result.getRecords());
        //总条数
        map.put("total",result.getTotal());
        //总页数
        map.put("pageCount",result.getPages());

        return map;
    }

}
