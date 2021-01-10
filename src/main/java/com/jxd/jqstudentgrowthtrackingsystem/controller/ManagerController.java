package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.*;
import com.jxd.jqstudentgrowthtrackingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @ClassName: ManagerController
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:44
 */
@RestController
public class ManagerController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IUserLoginService userLoginService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IJobService jobService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    IDeptEvaluatorService deptEvaluatorService;

    /*******************学生管理*******************/
    /**
     * 新增学员及学员用户
     * lt
     *
     * @param student 学员对象
     * @return
     */
    @RequestMapping("/saveThisStudent")
    public String saveThisStudent(@RequestBody Student student) {
        boolean addStudentFlag = studentService.save(student);
        UserLogin userLogin = new UserLogin();
        userLogin.setUserid(studentService.selectId(student).getStudentid());
        userLogin.setUsername(student.getStudentName());
        userLogin.setRole(3);
        boolean addStudentUserFlag = userLoginService.save(userLogin);
        if (addStudentFlag && addStudentUserFlag) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 修改学员信息和学员用户信息
     * lt
     *
     * @param student
     * @return
     */
    @RequestMapping("/editThisStudent")
    public String editThisStudent(@RequestBody Student student) {
        boolean editStudentFlag = studentService.updateById(student);
        boolean editUserFlag = userLoginService.updateUsername(student.getStudentName(), student.getStudentid());
        if (editStudentFlag && editUserFlag) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 删除学生及学生用户
     * lt
     *
     * @param studentids
     * @return
     */
    @RequestMapping("/removeAnyStudents")
    public String removeAnyStudents(@RequestBody Integer[] studentids) {
        boolean removeStudentsFlag = studentService.removeAnyStudents(studentids);
        boolean removeStudentUsersFlag = userLoginService.removeUser(studentids);
        if (removeStudentsFlag && removeStudentUsersFlag) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 获取全部用户
     * lt
     *
     * @param queryMap
     * @return 通过部门id获取部门信息，用于编辑时对表单的初始化赋值
     */
    @RequestMapping("/getAllUser")
    public Map<String, Object> getAllUser(@RequestBody Map<String, String> queryMap) {
        //获取每个查询参数
        int limit = Integer.parseInt(queryMap.get("limit"));
        int page = Integer.parseInt(queryMap.get("page"));
        String username = queryMap.get("filter");
        return userLoginService.getAllUser(limit, page, username);
    }

    /**
     * 获取全部学员
     * lt
     *
     * @param queryMap
     * @return
     */
    @RequestMapping("/getAllStudent")
    public Map<String, Object> getAllStudent(@RequestBody Map<String, String> queryMap) {
        //获取每个查询参数
        int limit = Integer.parseInt(queryMap.get("limit"));
        int page = Integer.parseInt(queryMap.get("page"));
        String studentName = queryMap.get("filter");
        String gradeid = queryMap.get("gradeid");
        return studentService.getAllstudent(limit, page, studentName, gradeid);
    }

    @GetMapping("/getDeptById/{deptno}")
    public Dept getEmpById(@PathVariable int deptno) {
        Dept dept = deptService.getById(deptno);
        return dept;
    }

    /**
     * 获取全部班期
     * lt
     *
     * @return 根据部门id 单个删除部门
     */
    @RequestMapping("/getAllGrade")
    public Map<String, Object> getAllGrade() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        List<Grade> gradeList = gradeService.list(queryWrapper.orderByDesc("gradeid"));
        List<Integer> gradeid = new ArrayList<>();
        for (int i = 0; i < gradeList.size(); i++) {
            gradeid.add(gradeList.get(i).getGradeid());
        }
        map.put("data", gradeid);
        return map;
    }

    /**
     * 获取学生职务
     *
     * @return 学生职务集合
     */
    @RequestMapping("/getStudentJob")
    public Map<String, Object> getStudentJob() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        map.put("data", jobService.list(queryWrapper.eq("jobid", 0)).get(0).getJobid());
        return map;
    }


    /********************************项目评价人*****************************/


    /**
     * 单个删除部门
     *  fws
     * @param deptno 部门号
     * @return 是否成功的标志
     */
    @PostMapping("/delDeptById")
    public String delDeptById(@RequestBody String deptno) {
        String deptid = deptno.substring(0, deptno.length() - 1);
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("deptno", deptid);
        int studentCountInDeptnos = studentService.count(wrapper);
        int evaluatorCountInDeptnos = deptEvaluatorService.count(wrapper);
        int count = studentCountInDeptnos + evaluatorCountInDeptnos;
        boolean isDel = false;
        if (count == 0) {
            isDel = deptService.removeById(deptid);
        }
        if (isDel) {
            return "success";
        } else {
            return "fail";
        }
    }


    /**
     * 批量删除部门
     * fws
     *
     * @param arrDeptnos 需要批量删除的部门id
     * @return 是否成功的字符串标记
     */
    @PostMapping("/delBatchDept")
    public String delBatchDept(@RequestBody String[] arrDeptnos) {
        List<String> deptnos = Arrays.asList(arrDeptnos);

        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.in("deptno", deptnos);
        int studentCountInDeptnos = studentService.count(wrapper);

        int evaluatorCountInDeptnos = deptEvaluatorService.count(wrapper);
        int count = studentCountInDeptnos + evaluatorCountInDeptnos;
        boolean isDel = false;
        if (count == 0) {
            isDel = deptService.removeByIds(deptnos);
        }
        if (isDel) {
            return "success";
        } else {
            return "fail";
        }
    }
}
