package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.Grade;

import java.util.List;
import java.util.Map;

public interface IGradeService extends IService<Grade> {
    /**
     * 查询所有未结课的班期
     * @return
     */
    List<Grade> selectAllGrade();


/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/4
 */

    Map<String,Object> getGradeByPage(int limit, int page);

    Map<String,Object> selectGrade(String gradeid,  int limit, int page);

    List<Grade> getGradeByTId(int tid);
}
