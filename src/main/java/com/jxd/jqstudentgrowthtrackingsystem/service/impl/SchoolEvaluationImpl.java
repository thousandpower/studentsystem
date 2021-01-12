package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.ICourseScoreDao;
import com.jxd.jqstudentgrowthtrackingsystem.dao.ISchoolEvaluationDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.CourseScore;
import com.jxd.jqstudentgrowthtrackingsystem.model.SchoolEvaluation;
import com.jxd.jqstudentgrowthtrackingsystem.service.ICourseScoreService;
import com.jxd.jqstudentgrowthtrackingsystem.service.ISchoolEvaluationService;
import org.springframework.stereotype.Service;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/11
 */
@Service
public class SchoolEvaluationImpl extends ServiceImpl<ISchoolEvaluationDao, SchoolEvaluation> implements ISchoolEvaluationService {
}
