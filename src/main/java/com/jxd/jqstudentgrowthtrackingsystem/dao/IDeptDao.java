package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxd.jqstudentgrowthtrackingsystem.model.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @ClassName: IDeptDao
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:45
 */
public interface IDeptDao extends BaseMapper<Dept> {

    IPage<Map<String,Object>> getDeptByPage(Page<Map<String,Object>> pages, @Param("deptname")String deptname);
}
