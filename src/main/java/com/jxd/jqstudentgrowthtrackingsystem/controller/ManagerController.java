package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.jxd.jqstudentgrowthtrackingsystem.model.Student;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import com.jxd.jqstudentgrowthtrackingsystem.service.IStudentService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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

    /**
     * 新增学员及学员用户
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
        if (addStudentFlag && addStudentUserFlag){
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 修改学员信息和学员用户信息
     * @param student
     * @return
     */
    @RequestMapping("/editThisStudent")
    public String editThisStudent(@RequestBody Student student){
        boolean editStudentFlag = studentService.updateById(student);
        UserLogin userLogin = userLoginService.getById(student.getStudentid());
        userLogin.setUsername(student.getStudentName());
        boolean editStudentUserFlag = userLoginService.updateById(userLogin);
        if (editStudentFlag && editStudentUserFlag){
            return "success";
        } else {
            return "fail";
        }
    }
    @RequestMapping("/removeAnyStudents")
    public String removeAnyStudents(Integer[] studentids){
        List<Integer> allStudentid = Arrays.asList(studentids);
        boolean removeStudentsFlag = studentService.removeByIds(allStudentid);
        boolean removeStudentUsersFlag = userLoginService.removeByIds(allStudentid);
        if (removeStudentsFlag && removeStudentUsersFlag){
            return "success";
        } else {
            return "fail";
        }
    }
}
