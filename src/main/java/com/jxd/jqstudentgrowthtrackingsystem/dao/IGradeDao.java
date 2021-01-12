package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxd.jqstudentgrowthtrackingsystem.model.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/4
 */
public interface IGradeDao extends BaseMapper<Grade> {
    IPage<Map<String,Object>> getGradeByPage(Page<Map<String,Object>> pages);

    IPage<Map<String,Object>> selectGrade(Page<Map<String,Object>> pages, @Param("gradeid")String gradeid);

    List<Grade> getGradeByTId(int tId);
}
