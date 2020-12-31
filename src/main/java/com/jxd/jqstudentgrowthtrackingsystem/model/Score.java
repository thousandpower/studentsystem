package com.jxd.jqstudentgrowthtrackingsystem.model;

/**
 * @ClassName Score
 * @Description TODO
 * @Author liutong
 * @Date 2020/12/31 9:26
 * @Version 1.0
 */
public class Score {
    //学生id
    private int studentid;
    //课程id
    private int courseid;
    //分数
    private int score;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
