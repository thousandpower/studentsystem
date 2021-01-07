package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IUserLoginDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import com.jxd.jqstudentgrowthtrackingsystem.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public UserLogin selectWithLogin(int userid, String password) {
        return userLoginDao.selectWithLogin(userid, password);
    }

    @Override
    public UserLogin selectThisUser(int userid) {
        return userLoginDao.selectThisUser(userid);
    }

    @Override
    public boolean updateThisUserPwd(UserLogin userLogin) {
        return userLoginDao.updateThisUserPwd(userLogin);
    }

    @Override
    public boolean resetThisUserPwd(Integer userid) {
        return userLoginDao.resetThisUserPwd(userid);
    }

    @Override
    public Map<String, Object> getAllUser(int limit, int page, String username) {
        //构造分页对象
        Page<Map<String, Object>> mapPage = new Page<>(page, limit);
        //将分页对象传递到dao层
        Map<String, Object> map = new HashMap<>();
        //调用dao层获取数据
        IPage<Map<String,Object>> result = userLoginDao.selectAllUser(mapPage,username);
        map.put("users",result.getRecords());
        map.put("total",result.getTotal());
        map.put("pageCount",result.getPages());
        return map;
    }

    @Override
    public UserLogin getMyPassword(Integer userid) {
        return userLoginDao.getMyPassword(userid);
    }

    @Override
    public boolean removeUser(Integer[] userid) {
        return userLoginDao.removeUser(userid);
    }

    @Override
    public boolean updateUsername(String username, Integer userid) {
        return userLoginDao.updateUsername(username, userid);
    }
}
