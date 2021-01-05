package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IMenuDao;
import com.jxd.jqstudentgrowthtrackingsystem.dao.ISchoolDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Menu;
import com.jxd.jqstudentgrowthtrackingsystem.model.SchoolEvaluation;
import com.jxd.jqstudentgrowthtrackingsystem.service.IMenuService;
import com.jxd.jqstudentgrowthtrackingsystem.service.ISchoolService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SchoolServiceImpl
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:49
 */
@Service
public class SchoolServiceImpl  extends ServiceImpl<ISchoolDao, SchoolEvaluation> implements ISchoolService {
    @Autowired
    private ISchoolDao schoolDao;


}
