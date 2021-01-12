package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName UserLogin
 * @Description TODO
 * @Author liutong
 * @Date 2020/12/31 9:24
 * @Version 1.0
 */
@TableName("userlogin")
public class UserLogin {
    //用户id
    @TableId
    private int userid;
    //用户名
    private String username;
    //密码
    private String password;
    //权限 0：管理员 1：学校评价人 2：项目部评价人 3：学员
    private int role;

    public UserLogin(int userid, String username, String password, int role) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserLogin() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
