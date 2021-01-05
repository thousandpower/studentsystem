package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserLoginDao extends BaseMapper<UserLogin> {
    /**
     * 登录验证
     * @param userid
     * @param password
     * @return
     */
    UserLogin selectWithLogin(@Param("userid") int userid, @Param("password") String password);

    /**
     * 查询当前用户的消息
     * @param userid
     * @return
     */
    UserLogin selectThisUser(@Param("userid") int userid);

    /**
     * 修改密码
     * @param userLogin
     * @return
     */
    boolean updateThisUserPwd(UserLogin userLogin);
    /**
     * 重置密码
     * @param userid
     * @return
     */
    boolean resetThisUserPwd(Integer userid);

    /**
     * 查询全部用户
     * @param page
     * @param username
     * @return
     */
    IPage<Map<String, Object>> selectAllUser(Page<Map<String,Object>> page,
                                                 @Param("username") String username);
}
