package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2020/12/31
 */
@TableName("grade")
public class Grade {
    @TableId("gradeid")
    /*班期号，第几期就是几号*/
    private int gradeid;

    /*教师编号，指定一位负责本期的教师*/
    private int teacherid;

    /*开班日期*/
    @TableField("date")
    private String startDate;

    public Grade(int gradeid, int teacherid) {
        this.gradeid = gradeid;
        this.teacherid = teacherid;
    }

    public Grade() {
    }

    public int getGradeid() {
        return gradeid;
    }

    public void setGradeid(int gradeid) {
        this.gradeid = gradeid;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
