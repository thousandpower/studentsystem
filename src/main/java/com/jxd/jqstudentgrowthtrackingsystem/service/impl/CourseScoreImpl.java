package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.ICourseScoreDao;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IGradeDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.CourseScore;
import com.jxd.jqstudentgrowthtrackingsystem.model.Grade;
import com.jxd.jqstudentgrowthtrackingsystem.service.ICourseScoreService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IGradeService;
import org.springframework.stereotype.Service;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/11
 */
@Service
public class CourseScoreImpl extends ServiceImpl<ICourseScoreDao, CourseScore> implements ICourseScoreService {
}
