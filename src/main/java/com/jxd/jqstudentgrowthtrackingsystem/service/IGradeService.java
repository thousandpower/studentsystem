package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.Grade;

import java.util.List;

public interface IGradeService extends IService<Grade> {
    /**
     * 查询所有未结课的班期
     * @return
     */
    List<Grade> selectAllGrade();
}
