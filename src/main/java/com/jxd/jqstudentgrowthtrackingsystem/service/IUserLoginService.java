package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;

public interface IUserLoginService extends IService<UserLogin> {
    UserLogin selectWithLogin(String username, String password);
}
