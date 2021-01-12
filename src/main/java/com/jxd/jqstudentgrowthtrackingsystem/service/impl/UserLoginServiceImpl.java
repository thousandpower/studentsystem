package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IUserLoginDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import com.jxd.jqstudentgrowthtrackingsystem.service.IUserLoginService;
import org.springframework.stereotype.Service;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/5
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<IUserLoginDao, UserLogin> implements IUserLoginService {
}
