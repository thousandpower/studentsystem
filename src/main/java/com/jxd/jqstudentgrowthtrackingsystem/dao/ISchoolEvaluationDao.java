package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluationItems;
import com.jxd.jqstudentgrowthtrackingsystem.model.SchoolEvaluation;
import com.jxd.jqstudentgrowthtrackingsystem.model.SchoolEvaluation;
import java.util.Map;

/**
 * @ClassName: ISchoolEvaluationDao
 * @Author: fws
 * @Description:
 * @Date: 2021/1/7 15:00
 */
public interface ISchoolEvaluationDao  extends BaseMapper<SchoolEvaluation> {
    /**
     * 获取学生的学校评价信息
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
