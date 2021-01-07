package com.jxd.jqstudentgrowthtrackingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IDeptEvaluationDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluator;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptEvaluationService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptEvaluatorService;
import com.jxd.jqstudentgrowthtrackingsystem.service.ISchoolEvaluationService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DeptController
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:43
 */
@RestController
public class DeptController {
    @Autowired
    private IDeptEvaluatorService deptEvaluatorService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ISchoolEvaluationService schoolEvaluationService;
    @Autowired
    private IDeptEvaluationService deptEvaluationService;


    /**
     * 用于总表中的学生信息展示
     * @param listQuery 分页信息，部门编号，模糊查询学生信息
     * @return 总表所需 学生信息
     */
    @PostMapping("/getStudentsByDeptno")
    public Map<String, Object> getStudentsByDeptno(@RequestBody Map<String, String> listQuery) {
        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 9 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page"));
        String studentname = listQuery.get("filter") == null ? "" : listQuery.get("filter");
        int evaluatorid = Integer.valueOf(listQuery.get("evaluatorid"));
        DeptEvaluator deptEvaluator = new DeptEvaluator();
        deptEvaluator = deptEvaluatorService.getById(evaluatorid);
        int deptno = deptEvaluator.getDeptno();

        Map<String, Object> map = new HashMap<>();
        map = studentService.getStudentByPageAndDeptno(studentname,deptno, limit, page);
        return map;
    }

    /**
     * 根据学生id 获取评分所需的所有内容
     * @param studentid 学生id
     * @return  学生的基础信息 以及 学校评价，转正评价，一二三年评价
     */
    @PostMapping("/getStudentAllInfoByStudentid")
    public Map<String,Object> getStudentAllInfoByStudentid(@RequestBody String studentid){
       int studentno =Integer.valueOf(studentid.substring(0, studentid.length() - 1)) ;

        Map<String, Object> map = new HashMap<>();
        //学生基础信息
        map.put("studentInfo", studentService.getById(studentno));
        //学校评价
        map.put("studentEvaInfoSc", schoolEvaluationService.getSchoolEvaluationInfo(studentno));
        //部门评价（0 1 2 3  分别为转正 一年 两年 三年评价）
        map.put("studentEvaInfo0",deptEvaluationService.getDeptEvaluationInfoByYear(studentno,0));
        map.put("studentEvaInfo1",deptEvaluationService.getDeptEvaluationInfoByYear(studentno,1));

        map.put("studentEvaInfo2",deptEvaluationService.getDeptEvaluationInfoByYear(studentno,2));

        map.put("studentEvaInfo3",deptEvaluationService.getDeptEvaluationInfoByYear(studentno,3));



        return map;
    }


}
