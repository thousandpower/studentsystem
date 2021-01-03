package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import org.apache.ibatis.annotations.Param;

public interface IUserLoginDao extends BaseMapper<UserLogin> {
    UserLogin selectWithLogin(@Param("username") String username, @Param("password") String password);
}
