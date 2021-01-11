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


    /**
     * 获取全部未结课的班期
     * lt
     *
     * @return 全部
     */
    @RequestMapping("/getNoEndGrade")
    public Map<String, Object> getNoEndGrade() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        List<Grade> gradeList = gradeService.selectAllGrade();
        List<Integer> gradeid = new ArrayList<>();
        for (int i = 0; i < gradeList.size(); i++) {
            gradeid.add(gradeList.get(i).getGradeid());
        }
        map.put("data", gradeid);
        return map;
    }

    /**
     * 获取全部的班期
     *  lt
     * @return 班期集合
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
     * lt
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
     * 获取职务信息 用于放在下拉框中
     * fws
     *
     * @return 职务信息集合
     */
    @GetMapping("/getJobs")
    public List<Dept> getJobs() {
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.ne("jobid", 0);
        wrapper.ne("jobid", 1);
        return jobService.list(wrapper);
    }

    /**
     * 获取部门信息 用于放在下拉框中
     * fws
     *
     * @return 部门信息集合
     */
    @GetMapping("/getDepts")
    public List<Dept> getDepts() {
        return deptService.list();
    }

    /**
     * 管理员部分的部门维护的 分页模糊查询的部门展示
     * fws
     *
     * @param listQuery 查询条件
     * @return 分页模糊查询所需数据
     */
    @PostMapping("/getDeptEvaluators")
    public Map<String, Object> getDeptEvaluators(@RequestBody Map<String, String> listQuery) {
        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 9 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page"));
        String deptEvaluatorName = listQuery.get("filter") == null ? "" : listQuery.get("filter");
        Map<String, Object> map = new HashMap<>();
        map = deptEvaluatorService.getDeptEvaluatorsByPage(deptEvaluatorName, limit, page);
        return map;
    }


    /**
     * 新增或编辑部门评价人
     * fws
     *
     * @param deptEvaluator 部门评价人
     * @return 是否成功的标志
     */
    @PostMapping("/addOrUpdDeptEvaluator")
    public String addOrUpdDeptEvaluator(@RequestBody DeptEvaluator deptEvaluator) {
        //用于判断是否是新增。
        boolean isAdd = false;
        //新增时，deptEvaluator.getEvaluatorid是默认为0
        if (deptEvaluator.getEvaluatorid() == 0) {
            isAdd = true;
        }
        boolean isAddOrUpdInDeEv = deptEvaluatorService.saveOrUpdate(deptEvaluator);
        boolean isAddOrUpdInUserLogin;
        //用于修改操作
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.set("username", deptEvaluator.getUsername());
        wrapper.set("password", "a123456");
        wrapper.set("role", 2);
        //如果是新增，且在部门评价人表中新增更新成功
        if (isAdd && isAddOrUpdInDeEv) {
            UserLogin userLogin = new UserLogin(deptEvaluator.getEvaluatorid(), deptEvaluator.getUsername(), "a123456", 2);
            isAddOrUpdInUserLogin = userLoginService.saveOrUpdate(userLogin);
            //如果是不是新增，即是修改，则需要进行用户表的更新操作
        } else {
            wrapper.eq("userid", deptEvaluator.getEvaluatorid());
            isAddOrUpdInUserLogin = userLoginService.update(wrapper);
        }

        if (isAddOrUpdInDeEv && isAddOrUpdInUserLogin) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 获取部门评价人
     * fws
     *
     * @param evaluatorid 评价人id
     * @return 部门评价人
     */
    @GetMapping("/getDeptEvaluatorById/{evaluatorid}")
    public DeptEvaluator getDeptEvaluatorById(@PathVariable int evaluatorid) {
        DeptEvaluator deptEvaluator = deptEvaluatorService.getById(evaluatorid);
        return deptEvaluator;
    }

    /**
     * 根据部门评价人id 单个删除部门评价人
     * fws
     *
     * @param evaluatorid 部门评价人id
     * @return 删除是否成功的字符串标志
     */
    @PostMapping("/delDeptEvaluatorById")
    public String delDeptEvaluatorById(@RequestBody String evaluatorid) {
        String evaluatoridSub = evaluatorid.substring(0, evaluatorid.length() - 1);
        DeptEvaluator deptEvaluator = deptEvaluatorService.getById(evaluatoridSub);
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("deptno", deptEvaluator.getDeptno());
        wrapper.eq("flag", 0);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("evaluatorid", evaluatoridSub);
        updateWrapper.set("flag", 1);
        int evaluatorCountInDeptnos = deptEvaluatorService.count(wrapper);
        boolean isDel = false;
        boolean isUpdFlag = false;
        if (evaluatorCountInDeptnos > 1) {
            isUpdFlag = deptEvaluatorService.update(updateWrapper);
            //更新离职状态后，删除登录表中的对应信息，无法登录
            if (isUpdFlag) {
                isDel = userLoginService.removeById(evaluatoridSub);
            }

        }
        if (isDel && isUpdFlag) {
            return "success";
        } else {
            return "fail";
        }
    }


    /*****************************部门维护******************************/

    /**
     * 管理员部分的部门维护的 分页模糊查询的部门展示
     * fws
     *
     * @param listQuery 查询条件
     * @return 分页模糊查询所需数据
     */
    @PostMapping("/getDeptsInfo")
    public Map<String, Object> getDepts(@RequestBody Map<String, String> listQuery) {
        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 10 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page"));
        String deptname = listQuery.get("filter") == null ? "" : listQuery.get("filter");
        Map<String, Object> map = new HashMap<>();
        map = deptService.getDeptByPage(deptname, limit, page);
        return map;
    }

    /**
     * 新增或编辑后的更新部门数据
     * fws
     *
     * @param dept 用于保存的部门数据
     * @return 成功或失败的字符串
     */
    @PostMapping("/addOrUpdDept")
    public String addOrUpdDept(@RequestBody Dept dept) {
        boolean isAddOrUpd = deptService.saveOrUpdate(dept);
        if (isAddOrUpd) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 通过部门id获取部门信息，用于编辑时对表单的初始化赋值
     * fws
     *
     * @param deptno 部门id
     * @return 查询出来的部门信息
     */
    @GetMapping("/getDeptById/{deptno}")
    public Dept getEmpById(@PathVariable int deptno) {
        Dept dept = deptService.getById(deptno);
        return dept;
    }

    /**
     * 根据部门id 单个删除部门
     * fws
     *
     * @param deptno 部门id
     * @return 删除是否成功的字符串标志
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
