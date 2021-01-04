package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluator;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @ClassName: IDeptEvaluatorDao
 * @Author: fws
 * @Description:
 * @Date: 2021/1/3 16:45
 */
public interface IDeptEvaluatorDao extends BaseMapper<DeptEvaluator> {

    IPage<Map<String,Object>> getDeptEvaluatorsByPage(Page<Map<String,Object>> pages, @Param("username")String username);
}
