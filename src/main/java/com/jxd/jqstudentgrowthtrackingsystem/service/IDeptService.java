package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.Dept;

import java.util.Map;

/**
 * @ClassName: IDeptService
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:47
 */
public interface IDeptService extends IService<Dept> {
    /**
     * 模糊分页查询部门信息
     *  fws
     * @param deptname
     * @param limit
     * @param page
     * @return
     */
    Map<String,Object> getDeptByPage(String deptname,int limit,int page);

}
