package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName: course
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 9:20
 */
@TableName("course")
public class Course {
    /*课程编号*/
    @TableId("courseid")
    private int courseid;

    /*课程名称*/
    private String coursename;

    /*课程状态 0:禁用 1：正在使用*/
    @TableField("course_state")
    private int courseState;

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getCourseState() {
        return courseState;
    }

    public void setCourseState(int courseState) {
        this.courseState = courseState;
    }

    public Course(int courseid, String coursename, int courseState) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.courseState = courseState;
    }

    public Course() {
    }
}
