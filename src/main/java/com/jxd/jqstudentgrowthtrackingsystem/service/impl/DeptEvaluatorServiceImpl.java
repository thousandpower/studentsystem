package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IDeptDao;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IDeptEvaluatorDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Dept;
import com.jxd.jqstudentgrowthtrackingsystem.model.Evaluator;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptEvaluatorService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DeptEvaluatorServiceImpl
 * @Author: fws
 * @Description:
 * @Date: 2021/1/3 16:44
 */
@Service
public class DeptEvaluatorServiceImpl  extends ServiceImpl<IDeptEvaluatorDao, Evaluator> implements IDeptEvaluatorService {
}
