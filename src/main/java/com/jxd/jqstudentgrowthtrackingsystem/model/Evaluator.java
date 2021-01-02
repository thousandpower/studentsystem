package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2020/12/31
 */
@TableName("evaluator")
public class Evaluator {
    @TableId("evaluatorid")
    /*评价人id*/
    private int evaluatorid;

    /*评价人姓名*/
    private String username;

    /*价人部门编号*/
    private int deptno;

    /*价人部门编号*/
    private int age;

    /*姓名*/
    private int sex;

    /*评价人手机号码*/
    private String phone;

    /*评价人是否离职*/
    private int flag;

    /*职务id*/
    private int jobid;


    public Evaluator(int evaluatorid, String username, int deptno, int age, int sex, String phone, int flag, int jobid) {
        this.evaluatorid = evaluatorid;
        this.username = username;
        this.deptno = deptno;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.flag = flag;
        this.jobid = jobid;
    }

    public Evaluator() {
    }


    public int getEvaluatorid() {
        return evaluatorid;
    }

    public void setEvaluatorid(int evaluatorid) {
        this.evaluatorid = evaluatorid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }
}
