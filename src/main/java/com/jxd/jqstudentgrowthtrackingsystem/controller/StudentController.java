package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.Course;
import com.jxd.jqstudentgrowthtrackingsystem.model.SchoolEvaluation;
import com.jxd.jqstudentgrowthtrackingsystem.model.Student;
import com.jxd.jqstudentgrowthtrackingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StudentController
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:43
 */
@RestController
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IDeptEvaluationService deptEvaluationService;
    @Autowired
    private IDeptEvaluationItemService deptEvaluationItemService;
    @Autowired
    private ISchoolService schoolService;
    @Autowired
    private IScoreService scoreService;
    @Autowired
    private IEvaluatorService evaluatorService;

    /**
     * 查询学员的所有信息
     *
     * @param userid
     * @return
     */
    @RequestMapping("/getMyInform/{userid}")
    public Map<String, Object> getMyInform(@PathVariable Integer userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", studentService.getById(userid));
        map.put("status", 200);
        return map;
    }

    /**
     * 修改学员信息
     *
     * @param student
     * @return
     */
    @RequestMapping("/editMyInform")
    public String editMyInform(Student student) {
        boolean flag = studentService.updateById(student);
        if (flag) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 查询学员的班期
     *
     * @param userid
     * @return
     */
    @RequestMapping("/getMyGrade/{userid}")
    public Map<String, Object> getMyGrade(@PathVariable Integer userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("gradeid", studentService.getById(userid).getGradeid());
        return map;
    }

    @RequestMapping("/getMySchoolEvaluation/{userid}")
    public Map<String, Object> getMySchoolEvaluation(@PathVariable Integer userid) {
        Map<String, Object> map = new HashMap<>();
        //班期
        map.put("gradeid", studentService.getById(userid).getGradeid());
        //评价人
        map.put("username", evaluatorService.getById(schoolService.getById(userid).getEvaluatorid()).getUsername());
        //综合评分
        map.put("appraisalScore", schoolService.getById(userid).getAppraisalScore());
        //评价
        map.put("appraisalContent", schoolService.getById(userid).getAppraisalContent());

        return map;
    }
}
