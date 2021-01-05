package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName: CourseScore
 * @Author: fws
 * @Description:
 * @Date: 2021/1/5 17:07
 */
@TableName("course_score")
public class CourseScore {
    /*学生id主键*/
    @TableId("studentid")
    private int studentid;
    /*html成绩*/
    private  int html;
    /*oracle成绩*/
    private int oracle;
    /*js成绩*/
    private int js;
    /*java基础 成绩*/
    @TableField("java_base")
    private int javaBase;
    /*java高级成绩*/
    @TableField("java_high")
    private int javaHigh;
    /*l1面试成绩*/
    private int l1;

    public CourseScore() {
    }

    public CourseScore(int studentid, int html, int oracle, int js, int javaBase, int javaHigh, int l1) {
        this.studentid = studentid;
        this.html = html;
        this.oracle = oracle;
        this.js = js;
        this.javaBase = javaBase;
        this.javaHigh = javaHigh;
        this.l1 = l1;
    }


    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getHtml() {
        return html;
    }

    public void setHtml(int html) {
        this.html = html;
    }

    public int getOracle() {
        return oracle;
    }

    public void setOracle(int oracle) {
        this.oracle = oracle;
    }

    public int getJs() {
        return js;
    }

    public void setJs(int js) {
        this.js = js;
    }

    public int getJavaBase() {
        return javaBase;
    }

    public void setJavaBase(int javaBase) {
        this.javaBase = javaBase;
    }

    public int getJavaHigh() {
        return javaHigh;
    }

    public void setJavaHigh(int javaHigh) {
        this.javaHigh = javaHigh;
    }

    public int getL1() {
        return l1;
    }

    public void setL1(int l1) {
        this.l1 = l1;
    }
}
