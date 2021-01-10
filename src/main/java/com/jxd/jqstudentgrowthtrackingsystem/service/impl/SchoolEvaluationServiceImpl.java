package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.ISchoolEvaluationDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.SchoolEvaluation;
import com.jxd.jqstudentgrowthtrackingsystem.service.ISchoolEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: SchoolEvaluationServiceImpl
 * @Author: fws
 * @Description:
 * @Date: 2021/1/7 14:59
 */
@Service
public class SchoolEvaluationServiceImpl extends ServiceImpl<ISchoolEvaluationDao, SchoolEvaluation> implements ISchoolEvaluationService {
    @Autowired
    private ISchoolEvaluationDao schoolEvaluationDao;

    /**
     * 获取学生的学校评价信息
     *  fws
     * @param studentno 学生编号
     * @return 学校评价信息集合
     */
    @Override
    public Map<String, Object> getSchoolEvaluationInfo(int studentno) {
        return schoolEvaluationDao.getSchoolEvaluationInfo(studentno);
    }
}
