package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName: DeptEvaluationItems
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 9:30
 */
@TableName("dept_evaluation_items")
public class DeptEvaluationItems {
    /*学生编号*/
    @TableId("studentid")
    private int studentid;

    /*能力*/
    private int ability;

    /*积极性*/
    private int initiative;

    /*沟通交流*/
    private int communication;

    /*人品*/
    @TableField("moral_quality")
    private int moralQuality;

    /*性格*/
    private int character;
    /*工作年份*/
    @TableField("work_year")
    private int workYear;

    public int getWorkYear() {
        return workYear;
    }

    public void setWorkYear(int workYear) {
        this.workYear = workYear;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getCommunication() {
        return communication;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
    }

    public int getMoralQuality() {
        return moralQuality;
    }

    public void setMoralQuality(int moralQuality) {
        this.moralQuality = moralQuality;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public DeptEvaluationItems(int studentid, int ability, int initiative, int communication, int moralQuality, int character) {
        this.studentid = studentid;
        this.ability = ability;
        this.initiative = initiative;
        this.communication = communication;
        this.moralQuality = moralQuality;
        this.character = character;
    }

    public DeptEvaluationItems() {
    }
}
