package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.jxd.jqstudentgrowthtrackingsystem.dao.IStudentDao;
import com.jxd.jqstudentgrowthtrackingsystem.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: IStudentService
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:50
 */
@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentDao studentDao;
}
