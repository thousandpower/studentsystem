package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.Evaluator;
import com.jxd.jqstudentgrowthtrackingsystem.model.Teacher;

import java.util.Map;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/4
 */
public interface ITeacherService extends IService<Teacher> {
    Map<String,Object> getTeacherByPage(String gradeid, int limit, int page);

    boolean delTeacherByBatch(String[] teacherids);

    boolean delTeacherById(int teacherid);

    Map<String,Object> getStudentByPage(String username, int limit, int page,int gradeid);

    Map<String,Object> getEvaluationBysId(int sId);

    Map<String,Object> getAssignList(String username, int limit, int page,int gradeid);

    boolean assignStudent(int studnetid,String hiredate,int jobid,int deptno);
}
