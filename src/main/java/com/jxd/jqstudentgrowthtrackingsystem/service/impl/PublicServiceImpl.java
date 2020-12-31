package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.jxd.jqstudentgrowthtrackingsystem.dao.IPublicDao;
import com.jxd.jqstudentgrowthtrackingsystem.service.IPublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: PublicServiceImpl
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:49
 */
@Service
public class PublicServiceImpl implements IPublicService {
    @Autowired
    private IPublicDao publicDao;
}
