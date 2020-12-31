package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.jxd.jqstudentgrowthtrackingsystem.dao.IManagerDao;
import com.jxd.jqstudentgrowthtrackingsystem.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ManagerServiceImpl
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:49
 */
@Service
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private IManagerDao managerDao;

}
