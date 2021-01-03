package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName: Job
 * @Author: fws
 * @Description:
 * @Date: 2021/1/2 17:21
 */
@TableName("job")
public class Job {

    /*职务id*/
    @TableId("jobid")
    private int jobid;

    /*职务名称*/
    private String jobname;

    public Job(int jobid, String jobname) {
        this.jobid = jobid;
        this.jobname = jobname;
    }

    public Job() {
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }
}
