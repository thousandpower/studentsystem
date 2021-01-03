package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IUserLoginDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import com.jxd.jqstudentgrowthtrackingsystem.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserLoginServiceImpl
 * @Description TODO
 * @Author liutong
 * @Date 2021/1/3 13:22
 * @Version 1.0
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<IUserLoginDao, UserLogin> implements IUserLoginService {
    @Autowired
    private IUserLoginDao userLoginDao;

    @Override
    public UserLogin selectWithLogin(String username, String password) {
        return userLoginDao.selectWithLogin(username, password);
    }
}
