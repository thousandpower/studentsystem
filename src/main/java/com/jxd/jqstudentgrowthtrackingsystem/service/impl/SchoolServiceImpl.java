package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.jxd.jqstudentgrowthtrackingsystem.dao.ISchoolDao;
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
public class SchoolServiceImpl implements ISchoolDao{
    @Autowired
    private ISchoolDao schoolDao;


}
