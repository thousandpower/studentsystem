package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.Dept;
import com.jxd.jqstudentgrowthtrackingsystem.model.Grade;
import com.jxd.jqstudentgrowthtrackingsystem.model.Job;
import com.jxd.jqstudentgrowthtrackingsystem.model.Teacher;
import com.jxd.jqstudentgrowthtrackingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName: SchoolController
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:44
 */
@RestController
public class SchoolController {
    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IGradeService gradeService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IJobService jobService;

    /**
     * @param
     * @Description: 获取全部班期列表
     * @Date: 2021/1/12
     */
    @PostMapping("/getAllGrade")
    public Map<String, Object> getAllGrade(@RequestBody Map<String, String> listQuery) {
        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 10 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page")); //分页条件
        String gradeid = listQuery.get("filter"); //查询条件
        Map<String, Object> map = new HashMap<>();
        if (gradeid == "") { //若为空直接查询所有列表
            map = gradeService.getGradeByPage(limit, page);

        } else { //若不为空按条件查询对应班期
            map = gradeService.selectGrade(gradeid, limit, page);
        }
        return map;
    }

    /**
     * @param
     * @Description: 获取教师列表，用于下拉框
     * @Date: 2021/1/12
     */
    @GetMapping("/getTeachers")
    public List<Teacher> getTeachers() {
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("flag", 0); //flag为0时表示教师在职
        return teacherService.list(wrapper);
    }

    /**
     * @param
     * @Description: 新增或维护班期
     * @Date: 2021/1/12
     */
    @PostMapping("/addOrUpdGrade")
    public String addOrUpdGrade(@RequestBody Grade grade) {
        if (gradeService.saveOrUpdate(grade)) {
            return "success";
        } else {
            return "fail";
        }
    }


    /**
     * @param
     * @Description: 批量删除班期
     * @Date: 2021/1/12
     */
    @PostMapping("/delBatchGrade")
    public String delBatchGrade(@RequestBody String[] arrGrade) {
        List<String> gradeids = Arrays.asList(arrGrade);
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.in("gradeid", gradeids); //构造条件，查询班期下有无学生
        int studentInGrades = studentService.count(wrapper);
        boolean isDel = false;
        if (studentInGrades == 0) { //如果无学生，班期可以删除
            isDel = gradeService.removeByIds(gradeids);
        }
        if (isDel) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * @param
     * @Description: 根据班期号得到班期信息
     * @Date: 2021/1/12
     */
    @GetMapping("/getGradeById/{gradeid}")
    public Grade getGradeById(@PathVariable int gradeid) {
        Grade grade = gradeService.getById(gradeid);
        return grade;
    }


    /**
     * @param
     * @Description: 根据教师id得到教师所教授的所有班期列表，用于下拉框
     * @Date: 2021/1/12
     */
    @GetMapping("/getGradeByTId/{tId}")
    public List<Grade> getGradeByTId(@PathVariable int tId) {
        List<Grade> list = new ArrayList<>();
        list = gradeService.getGradeByTId(tId);
        return list;
    }

    /**
     * @param
     * @Description: 获得指定班期下的所有在职的学生列表
     * @Date: 2021/1/12
     */
    @PostMapping("/getStudentByPage")
    public Map<String, Object> getStudentByPage(@RequestBody Map<String, String> listQuery) {
        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 10 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page"));//分页条件
        int gradeid = Integer.valueOf(listQuery.get("gradeid")); //指定班期号
        String username = listQuery.get("filter") == null ? "" : listQuery.get("filter"); //模糊查询条件

        Map<String, Object> map = new HashMap<>();
        map = teacherService.getStudentByPage(username, limit, page, gradeid);
        return map;
    }

    /**
     * @param
     * @Description: 获得所有部门列表，用于下拉框
     * @Date: 2021/1/12
     */
    @GetMapping("/getAllDept")
    public List<Dept> getAllDept() {
        return deptService.list();
    }

    /**
     * @param
     * @Description: 获得所有职位列表，用于下拉框
     * @Date: 2021/1/12
     */
    @GetMapping("/getAllJobs")
    public List<Job> getAllJobs() {
        return jobService.list();
    }
}
