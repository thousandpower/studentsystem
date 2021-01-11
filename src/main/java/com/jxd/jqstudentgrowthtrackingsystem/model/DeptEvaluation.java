package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: DeptEvaluation
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 9:28
 */
@TableName("dept_evaluation")
public class DeptEvaluation {
    /*学生编号*/
    @TableId("studentid")
    private int studentid;

    /*评价人编号*/
    private int evaluatorid;

    /*评价分数*/
    @TableField("appraisal_score")
    private int appraisalScore;

    /*评价内容*/
    @TableField("appraisal_content")
    private String appraisalContent;
    /*工作年份*/
    @TableField("work_year")
    private int workYear;
    /*部门编号*/
    private int deptno;
    /*职务编号*/
    private int jobid;
    /*保存时间，非编辑*/
    private String savetime;


    public DeptEvaluation(int studentid, int evaluatorid, int appraisalScore, String appraisalContent, int workYear, int deptno, int jobid) {
        this.studentid = studentid;
        this.evaluatorid = evaluatorid;
        this.appraisalScore = appraisalScore;
        this.appraisalContent = appraisalContent;
        this.workYear = workYear;
        this.deptno = deptno;
        this.jobid = jobid;
    }

    public DeptEvaluation(int studentid, int evaluatorid, int appraisalScore, String appraisalContent, int workYear, int deptno, int jobid, String savetime) {
        this.studentid = studentid;
        this.evaluatorid = evaluatorid;
        this.appraisalScore = appraisalScore;
        this.appraisalContent = appraisalContent;
        this.workYear = workYear;
        this.deptno = deptno;
        this.jobid = jobid;
        this.savetime = savetime;
    }


    public DeptEvaluation() {
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getEvaluatorid() {
        return evaluatorid;
    }

    public void setEvaluatorid(int evaluatorid) {
        this.evaluatorid = evaluatorid;
    }

    public int getAppraisalScore() {
        return appraisalScore;
    }

    public void setAppraisalScore(int appraisalScore) {
        this.appraisalScore = appraisalScore;
    }

    public String getAppraisalContent() {
        return appraisalContent;
    }

    public void setAppraisalContent(String appraisalContent) {
        this.appraisalContent = appraisalContent;
    }

    public int getWorkYear() {
        return workYear;
    }

    public void setWorkYear(int workYear) {
        this.workYear = workYear;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String getSavetime() {
        return savetime;
    }

    public void setSavetime(String savetime) {
        this.savetime = savetime;
    }
}

