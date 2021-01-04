package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IDeptDao;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IUserLoginDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Dept;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IUserLoginService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: IUserLoginServiceImpl
 * @Author: fws
 * @Description:
 * @Date: 2021/1/4 19:27
 */
@Service
public class IUserLoginServiceImpl extends ServiceImpl<IUserLoginDao, UserLogin> implements IUserLoginService {
}
