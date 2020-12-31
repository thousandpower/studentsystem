package com.jxd.jqstudentgrowthtrackingsystem.model;

/**
 * @ClassName UserLogin
 * @Description TODO
 * @Author liutong
 * @Date 2020/12/31 9:24
 * @Version 1.0
 */
public class UserLogin {
    //用户id
    private int userid;
    //用户名
    private String username;
    //密码
    private String password;
    //权限 0：管理员 1：学校评价人 2：项目部评价人 3：学员
    private int role;

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
