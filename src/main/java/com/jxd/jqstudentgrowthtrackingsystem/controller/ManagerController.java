package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.*;
import com.jxd.jqstudentgrowthtrackingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        UserLogin userLogin = userLoginService.getById(student.getStudentid());
        userLogin.setUsername(student.getStudentName());
        boolean editStudentUserFlag = userLoginService.updateById(userLogin);
        if (editStudentFlag && editStudentUserFlag) {
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
    public String removeAnyStudents(Integer[] studentids) {
        List<Integer> allStudentid = Arrays.asList(studentids);
        boolean removeStudentsFlag = studentService.removeAnyStudents(studentids);
        boolean removeStudentUsersFlag = userLoginService.removeByIds(allStudentid);
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
    @RequestMapping("/getAllStudent")
    public Map<String, Object> getAllStudent(@RequestBody Map<String, String> queryMap) {
        //获取每个查询参数
        int limit = Integer.parseInt(queryMap.get("limit"));
        int page = Integer.parseInt(queryMap.get("page"));
        String studentName = queryMap.get("filter");
        return null;
    }
}
