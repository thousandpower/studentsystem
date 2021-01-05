package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IUserLoginDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Dept;
import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluator;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import com.jxd.jqstudentgrowthtrackingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private IDeptService deptService;
    @Autowired
    private IDeptEvaluatorService deptEvaluatorService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IJobService jobService;
    @Autowired
    private IUserLoginService userLoginService;

    /********************************项目评价人*****************************/


    /**
     * 获取职务信息 用于放在下拉框中
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
     *
     * @return 部门信息集合
     */
    @GetMapping("/getDepts")
    public List<Dept> getDepts() {
        return deptService.list();
    }

    /**
     * 管理员部分的部门维护的 分页模糊查询的部门展示
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

        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.set("username", deptEvaluator.getUsername());
        wrapper.set("password", "a123456");
        wrapper.set("role", 2);

        //如果是新增，且在部门评价人表中新增更新成功
        if (isAdd && isAddOrUpdInDeEv) {

            UserLogin userLogin = new UserLogin(deptEvaluator.getEvaluatorid(), deptEvaluator.getUsername(), "a123456", 2);
            isAddOrUpdInUserLogin = userLoginService.saveOrUpdate(userLogin);
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

    @GetMapping("/getDeptEvaluatorById/{evaluatorid}")
    public DeptEvaluator getDeptEvaluatorById(@PathVariable int evaluatorid) {
        DeptEvaluator deptEvaluator = deptEvaluatorService.getById(evaluatorid);
        return deptEvaluator;
    }
    /**
     * 根据部门评价人id 单个删除部门评价人
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
        wrapper.eq("flag",0);

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("evaluatorid",evaluatoridSub);
        updateWrapper.set("flag",1);

        int evaluatorCountInDeptnos = deptEvaluatorService.count(wrapper);
        boolean isDel = false;
        boolean isUpdFlag = false;
        if (evaluatorCountInDeptnos > 1) {

            isUpdFlag = deptEvaluatorService.update(updateWrapper);
            if (isUpdFlag){
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
     *
     * @param listQuery 查询条件
     * @return 分页模糊查询所需数据
     */
    @PostMapping("/getDepts")
    public Map<String, Object> getDepts(@RequestBody Map<String, String> listQuery) {
        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 9 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page"));
        String deptname = listQuery.get("filter") == null ? "" : listQuery.get("filter");
        Map<String, Object> map = new HashMap<>();
        map = deptService.getDeptByPage(deptname, limit, page);
        return map;
    }

    /**
     * 新增或编辑后的更新部门数据
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
