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

    @RequestMapping("/saveThisStudent")
    public String saveThisStudent(@RequestBody Student student) {
        boolean addStudentFlag = studentService.save(student);
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(student.getStudentName());
        userLogin.setRole(3);
        boolean addUserFlag = userLoginService.save(userLogin);
        if (addStudentFlag && addUserFlag) {
            return "success";
        } else {
            return "fail";
        }
    }
}
