package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.Grade;

import java.util.List;

public interface IGradeDao extends BaseMapper<Grade> {
    /**
     * 查询所有未结课的班期
     * @return
     */
    List<Grade> selectAllGrade();
}
