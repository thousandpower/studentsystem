package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.jxd.jqstudentgrowthtrackingsystem.dao.IDeptDao;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DeptServiceImpl
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:49
 */
@Service
public class DeptServiceImpl implements IDeptService {
    @Autowired
    private IDeptDao deptDao;
}
