package com.jxd.jqstudentgrowthtrackingsystem.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IDeptEvaluationDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluation;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluationItems;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluator;
import com.jxd.jqstudentgrowthtrackingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    @Autowired
    private IDeptEvaluationItemService deptEvaluationItemService;

    /**
     * 用于总表中的学生信息展示
     *  fws
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
        map = studentService.getStudentByPageAndDeptno(studentname, deptno, limit, page);
        return map;
    }

    /**
     * 根据学生id 获取评分所需的所有内容
     *  fws
     * @param studentid 学生id
     * @return 学生的基础信息 以及 学校评价，转正评价，一二三年评价
     */
    @PostMapping("/getStudentAllInfoByStudentid")
    public Map<String, Object> getStudentAllInfoByStudentid(@RequestBody String studentid) {
        int studentno = Integer.valueOf(studentid.substring(0, studentid.length() - 1));
        Map<String, Object> map = new HashMap<>();
        //学生基础信息
        map.put("studentInfo", studentService.getStudentById(studentno));
        //学校评价
        map.put("studentEvaInfoSc", schoolEvaluationService.getSchoolEvaluationInfo(studentno));
        //部门评价（0 1 2 3  分别为转正 一年 两年 三年评价）
        map.put("studentEvaInfo0", deptEvaluationService.getDeptEvaluationInfoByYear(studentno, 0));
        map.put("studentEvaInfo1", deptEvaluationService.getDeptEvaluationInfoByYear(studentno, 1));
        map.put("studentEvaInfo2", deptEvaluationService.getDeptEvaluationInfoByYear(studentno, 2));
        map.put("studentEvaInfo3", deptEvaluationService.getDeptEvaluationInfoByYear(studentno, 3));

        //java.lang.String 转化为 java.util.Date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse((String) studentService.getStudentById(studentno).get("hiredate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //计算时间差，后判定当前时间与入职时间的差值
        //Calendar  :距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00.000，格里高利历）的偏移量
        Calendar hiredate = Calendar.getInstance();
        Calendar currentTime = Calendar.getInstance();
        Date currentDate = new Date();
        currentTime.setTime(currentDate);
        hiredate.setTime(date);
        long currentTimeL = currentTime.getTimeInMillis();
        long hiredateL = hiredate.getTimeInMillis();
        //乘法溢出int上限，要么转long 要么拆除个数来
        long month = (currentTimeL - hiredateL) / (1000 * 60 * 60 * 24) / 30;
        map.put("workMonth", month);
        return map;
    }

    /**
     * 添加或更新评价信息
     *  fws
     * @param map 评价的表单信息
     * @return 成功或失败的标识
     */
    @PostMapping("/addOrUpdDeptEvaluation")
    public String addOrUpdDeptEvaluation(@RequestBody Map<String, String> map) {

        int studentid = Integer.parseInt(map.get("studentid"));
        int evaluatorid = Integer.parseInt(map.get("evaluatorid"));
        int appraisalScore = Integer.parseInt(map.get("appraisal_score"));
        String appraisalContent = map.get("appraisal_content");
        int workYear = Integer.parseInt(map.get("work_year"));
        int deptno = Integer.parseInt(map.get("deptno"));
        int jobid = Integer.parseInt(map.get("jobid"));

        DeptEvaluation deptEvaluation = new DeptEvaluation(studentid, evaluatorid, appraisalScore, appraisalContent, workYear, deptno, jobid);
        boolean isAddOrUpd = deptEvaluationService.save(deptEvaluation);

        int ability = Integer.parseInt(map.get("ability"));
        int initiative = Integer.parseInt(map.get("initiative"));
        int communication = Integer.parseInt(map.get("communication"));
        int moralQuality = Integer.parseInt(map.get("moral_quality"));
        int characters = Integer.parseInt(map.get("characters"));

        DeptEvaluationItems deptEvaluationItems = new DeptEvaluationItems(studentid, ability, initiative, communication, moralQuality, characters, workYear);
        boolean isAddOrUpdItems = deptEvaluationItemService.save(deptEvaluationItems);
        if (isAddOrUpd && isAddOrUpdItems) {
            return "success";
        } else {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("studentid", studentid);
            wrapper.eq("work_year", workYear);
            deptEvaluationService.remove(wrapper);
            deptEvaluationItemService.remove(wrapper);
            return "fail";
        }
    }

}
