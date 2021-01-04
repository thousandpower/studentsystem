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
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IScoreService scoreService;

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
     * 查询所有学生
     *
     * @param student
     * @return
     */
    @RequestMapping("/getAllStudents")
    public Map<String, Object> getAllStudents(Student student) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.orderByDesc("gradeid");
        if (student != null){
            studentQueryWrapper.like("student_name",student.getStudentName());
            studentQueryWrapper.eq("gradeid",student.getGradeid());
        }
        List<Student> studentList = studentService.list(studentQueryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("data", studentList);
        return map;
    }

    /**
     * 查询所有班期
     * @return
     */
    @RequestMapping("/getAllGrade")
    public Map<String, Object> getAllGrade() {
        List<Grade> gradeList = gradeService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("data", gradeList);
        return map;
    }

    /**
     * 查询所有课程
     * @return
     */
    @RequestMapping("/getAllCourse")
    public Map<String,Object> getAllCourse(){
        List<Course> courseList = courseService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("data", courseList);
        return map;
    }
}
