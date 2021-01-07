package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.*;
import com.jxd.jqstudentgrowthtrackingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    /**
     * 新增学员及学员用户
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
     *
     * @param student
     * @return
     */
    @RequestMapping("/editThisStudent")
    public String editThisStudent(@RequestBody Student student) {
        boolean editStudentFlag = studentService.updateById(student);
        boolean editUserFlag = userLoginService.updateUsername(student.getStudentName(),student.getStudentid());
        if (editStudentFlag && editUserFlag) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 删除学生及学生用户
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
     *
     * @param queryMap
     * @return
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
        return studentService.getAllstudent(limit, page, studentName,gradeid);
    }

    /**
     * 获取全部班期
     *
     * @return
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

    @RequestMapping("/getStudentJob")
    public Map<String, Object> getStudentJob() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        map.put("data", jobService.list(queryWrapper.eq("jobid", 5)).get(0).getJobid());
        return map;
    }
}
