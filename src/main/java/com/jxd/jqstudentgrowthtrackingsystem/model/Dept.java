package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName: Dept
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 9:27
 */
@TableName("dept")
public class Dept {
    /*部门编号 0：学校 1：研发部 2：*/
    @TableId("deptno")
    private int deptno;

    /*部门名称*/
    private String deptname;

    /*部门的详细描述*/
    private String description;

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dept(int deptno, String deptname, String description) {
        this.deptno = deptno;
        this.deptname = deptname;
        this.description = description;
    }

    public Dept() {
    }
}
