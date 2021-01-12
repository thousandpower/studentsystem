package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IJobDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Job;
import com.jxd.jqstudentgrowthtrackingsystem.service.IJobService;
import org.springframework.stereotype.Service;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/11
 */
@Service
public class JobServiceImpl extends ServiceImpl<IJobDao, Job> implements IJobService {
}
