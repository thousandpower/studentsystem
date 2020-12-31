package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2020/12/30
 */
@TableName("menu")
public class Menu {
    @TableId("id")
    /*菜单编号*/
    private int id;

    /*菜单标题*/
    private String title;

    /*菜单对应的路径*/
    private String path;

    /*对应的权限*/
    private int authority;

    public Menu() {
    }

    public Menu(int id, String title, String path, int authority) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
