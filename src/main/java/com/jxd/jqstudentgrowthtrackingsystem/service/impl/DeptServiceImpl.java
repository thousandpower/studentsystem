package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IDeptDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Dept;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DeptServiceImpl
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:49
 */
@Service
public class DeptServiceImpl extends ServiceImpl<IDeptDao, Dept> implements IDeptService {
    @Autowired
    private IDeptDao deptDao;

    /**
     * 模糊分页查询部门信息
     *  fws
     * @param deptname
     * @param limit
     * @param page
     * @return
     */
    @Override
    public Map<String, Object> getDeptByPage(String deptname, int limit, int page) {
        //构造分页对象
        Page<Map<String, Object>> pages = new Page<>(page, limit);
        Map<String, Object> map = new HashMap<>();
        //调用dao层获取数据
        IPage<Map<String, Object>> result = deptDao.getDeptByPage(pages, deptname);
        //查询出的部门信息 （分页）
        map.put("depts", result.getRecords());
        //总条数
        map.put("total", result.getTotal());
        //总页数
        map.put("pageCount", result.getPages());
        return map;
    }


}
