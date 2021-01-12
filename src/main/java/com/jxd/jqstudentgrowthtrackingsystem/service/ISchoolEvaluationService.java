package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.Menu;
import com.jxd.jqstudentgrowthtrackingsystem.model.SchoolEvaluation;
import com.jxd.jqstudentgrowthtrackingsystem.model.SchoolEvaluation;
import com.jxd.jqstudentgrowthtrackingsystem.model.SchoolEvaluation;

import java.util.Map;

/**
 * @ClassName: ISchoolEvaluationService
 * @Author: fws
 * @Description:
 * @Date: 2021/1/7 14:59
 */
public interface ISchoolEvaluationService extends IService<SchoolEvaluation> {
    /**
     * 获取学生的学校评价信息
     *  fws
     * @param studentno 学生编号
     * @return 学校评价信息集合
     */
    Map<String, Object> getSchoolEvaluationInfo(int studentno);

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/11
 */


}
