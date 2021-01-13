package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.jxd.jqstudentgrowthtrackingsystem.model.*;
import com.jxd.jqstudentgrowthtrackingsystem.service.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/4
 */
@RestController
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IGradeService gradeService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private ICourseScoreService courseScoreService;

    @Autowired
    private ISchoolEvaluationService schoolEvaluationService;

    /**
     * @param
     * @Description: 获得所有在职的教师信息
     * @Date: 2021/1/12
     */
    @PostMapping("/getAllTeacher")
    public Map<String, Object> getAllTeacher(@RequestBody Map<String, String> listQuery) {
        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 10 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page"));//分页条件
        String username = listQuery.get("filter") == null ? "" : listQuery.get("filter"); //模糊查询条件
        Map<String, Object> map = new HashMap<>();
        map = teacherService.getTeacherByPage(username, limit, page);
        return map;
    }

    /**
     * @param
     * @Description: 根据教师id获取对应的教师信息
     * @Date: 2021/1/12
     */
    @GetMapping("/getTeacherById/{teacherid}")
    public Teacher getTeacherById(@PathVariable int teacherid) {
        return teacherService.getById(teacherid);
    }

    /**
     * @param
     * @Description: 新增或修改教师
     * @Date: 2021/1/12
     */
    @PostMapping("/addOrUpdTeacher")
    public String addOrUpdTeacher(@RequestBody Teacher teacher) {
        teacher.setFlag(0);
        if (teacher.getTeacherid() == 0) { //判断为新增时，同时也向登录表插入新增的数据，使新增教师可以登录
            teacherService.save(teacher);
            UserLogin userLogin = new UserLogin(teacher.getTeacherid(), teacher.getUsername(), "a123456", 1);
            if (userLoginService.save(userLogin)) {
                ;
                return "success";
            } else {
                return "fail";
            }
        } else { //判断为修改时，同时修改两个表中的数据
            teacherService.saveOrUpdate(teacher);
            UserLogin userLogin = new UserLogin(teacher.getTeacherid(), teacher.getUsername(), "a123456", 1);
            if (userLoginService.saveOrUpdate(userLogin)) {
                return "success";
            } else {
                return "fail";
            }
        }
    }

    /**
     * @param
     * @Description: 批量删除教师
     * @Date: 2021/1/12
     */
    @PostMapping("/delBatchTeacher")
    public String delBatchTeacher(@RequestBody String[] arrTeacher) {
        List<String> userids = Arrays.asList(arrTeacher);//接收批量删除的教师id数组
        if (teacherService.delTeacherByBatch(arrTeacher) && userLoginService.removeByIds(userids)) {
            //删除教师，将教师状态置为0，登录表中直接删除对应的教师登录信息
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * @param
     * @Description: 按行删除教师
     * @Date: 2021/1/12
     */
    @GetMapping("/delTeacherById/{teacherid}")
    public String delTeacherById(@PathVariable int teacherid) {
        if (teacherService.delTeacherById(teacherid) && userLoginService.removeById(teacherid)) {
            //删除时，将教师状态置为0，并且删除登录表中的信息
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * @param
     * @Description: 根据教师所带的学生id查询该学生信息
     * @Date: 2021/1/12
     */
    @GetMapping("/getInfoBysId/{sId}")
    public Student getInfoBysId(@PathVariable int sId) {
        Student student = studentService.getById(sId);
        return student;
    }

    /**
     * @param
     * @Description: 根据学生id获取对应的评价信息
     * @Date: 2021/1/12
     */
    @GetMapping("/getEvaluationBysId/{sId}")
    public Map<String, Object> getEvaluationBysId(@PathVariable int sId) {
        return teacherService.getEvaluationBysId(sId);
    }

    /**
     * @param
     * @Description: 为学生进行打分操作
     * @Date: 2021/1/12
     */
    @PostMapping("/marking")
    public String marking(@RequestBody Map<String, String> formEvaInfoSc) {
        //接收到学校的评价信息
        SchoolEvaluation schoolEvaluation = new SchoolEvaluation(Integer.valueOf(formEvaInfoSc.get("studentid")),
                Integer.valueOf(formEvaInfoSc.get("evaluatorid")),
                Integer.valueOf(formEvaInfoSc.get("gradeid")),
                Integer.valueOf(formEvaInfoSc.get("appraisal_score")),
                formEvaInfoSc.get("appraisal_content"));

        //接收到课程的成绩信息
        CourseScore courseScore = new CourseScore(
                Integer.valueOf(formEvaInfoSc.get("studentid")), Integer.valueOf(formEvaInfoSc.get("html")),
                Integer.valueOf(formEvaInfoSc.get("oracle")), Integer.valueOf(formEvaInfoSc.get("js")),
                Integer.valueOf(formEvaInfoSc.get("java_base")), Integer.valueOf(formEvaInfoSc.get("java_high")),
                Integer.valueOf(formEvaInfoSc.get("l1"))
        );
        if (courseScoreService.saveOrUpdate(courseScore) && schoolEvaluationService.saveOrUpdate(schoolEvaluation)) {
            //将信息插入到两张表中
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * @param
     * @Description: 根据学生id获取这个学生信息
     * @Date: 2021/1/12
     */
    @GetMapping("/getThisStudent/{studentid}")
    public Student getThisStudent(@PathVariable int studentid) {
        return studentService.getById(studentid);
    }


    /**
     * @param
     * @Description: 获取可以进行入职的学生列表(学校总评成绩大于等于3)
     * @Date: 2021/1/12
     */
    @PostMapping("/getAssignmentList")
    public Map<String, Object> getAssignmentList(@RequestBody Map<String, String> listQuery) {
        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 10 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page"));//分页条件
        int gradeid = Integer.valueOf(listQuery.get("gradeid"));//对应班期条件

        String username = listQuery.get("filter") == null ? "" : listQuery.get("filter");//模糊查询条件

        Map<String, Object> map = new HashMap<>();
        map = teacherService.getAssignList(username, limit, page, gradeid);
        return map;
    }

    /**
     * @param
     * @Description: 办理分配部门操作
     * @Date: 2021/1/12
     */
    @PostMapping("/assign")
    public String assign(@RequestBody Map<String, String> form) {
        int studentid = Integer.valueOf(form.get("studentid")); //获取分配的学生id
        int jobid=1;
        int deptno = Integer.valueOf(form.get("deptno")); //获取分配的职务id
        String hiredate = form.get("hiredate");  //入职日期

        if (teacherService.assignStudent(studentid, hiredate, jobid, deptno)) {
            return "success";
        } else {
            return "fail";
        }
    }
}
