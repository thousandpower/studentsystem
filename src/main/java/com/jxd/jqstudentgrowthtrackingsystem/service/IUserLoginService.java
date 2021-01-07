package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface IUserLoginService extends IService<UserLogin> {
    /**
     * 登录
     *
     * @param userid
     * @param password
     * @return
     */
    UserLogin selectWithLogin(int userid, String password);

    /**
     * 查询该用户的信息
     *
     * @param userid
     * @return
     */
    UserLogin selectThisUser(int userid);

    /**
     * 修改密码
     *
     * @param userLogin
     * @return
     */
    boolean updateThisUserPwd(UserLogin userLogin);

    /**
     * 重置密码
     *
     * @param userid
     * @return
     */
    boolean resetThisUserPwd(Integer userid);

    /**
     * 查询全部用户
     *
     * @param limit
     * @param page
     * @param ename
     * @return
     */
    Map<String, Object> getAllUser(int limit, int page, String ename);

    /**
     * 查询用户的密码
     *
     * @param userid
     * @return
     */
    UserLogin getMyPassword(Integer userid);

    /**
     * 批量删除
     *
     * @param userid
     * @return
     */
    boolean removeUser(Integer[] userid);

    /**
     * 修改用户名
     *
     * @param username
     * @param userid
     * @return
     */
    boolean updateUsername(String username, Integer userid);
}
