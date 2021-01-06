package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.jxd.jqstudentgrowthtrackingsystem.model.Student;
import com.jxd.jqstudentgrowthtrackingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    private IDeptEvaluationService deptEvaluationService;
    @Autowired
    private IDeptEvaluationItemService deptEvaluationItemService;
    @Autowired
    private ISchoolService schoolService;
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
    public String editMyInform(@RequestBody Student student) {
        boolean flag = studentService.updateById(student);
        if (flag) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 获取培训学校评价
     *
     * @param userid
     * @return
     */
    @RequestMapping("/getMySchoolEvaluation/{userid}")
    public Map<String, Object> getMySchoolEvaluation(@PathVariable Integer userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", studentService.selectThisStudentSchoolEvaluation(userid));
        return map;
    }

    /**
     * 获取部门评价
     *
     * @param userid
     * @param workYear
     * @return
     */
    @RequestMapping("/getMyDeptEvaluation/{userid}/{workYear}")
    public Map<String, Object> getMyDeptEvaluation(@PathVariable Integer userid, @PathVariable Integer workYear) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", studentService.selectThisStudentDeptEvaluation(userid, workYear));
        return map;
    }
}
