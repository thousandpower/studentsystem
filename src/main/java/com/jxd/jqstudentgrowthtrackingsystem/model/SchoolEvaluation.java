package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2020/12/31
 */
@TableName("school_evaluation")
public class SchoolEvaluation {
    @TableId("studentid")
    /*学员编号*/
    private int studentid;

    /*对应的评价人编号*/
    private int evaluatorid;

    /*学员所在的班期*/
    private int gradeid;

    @TableField("appraisal_score")
    /*综合评分*/
    private int appraisalScore;

    @TableField("appraisal_content")
    /*综合的评价内容*/
    private String appraisalContent;

    public SchoolEvaluation() {
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

    public int getGradeid() {
        return gradeid;
    }

    public void setGradeid(int gradeid) {
        this.gradeid = gradeid;
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

    public SchoolEvaluation(int studentid, int evaluatorid, int gradeid, int appraisalScore, String appraisalContent) {
        this.studentid = studentid;
        this.evaluatorid = evaluatorid;
        this.gradeid = gradeid;
        this.appraisalScore = appraisalScore;
    }
}
