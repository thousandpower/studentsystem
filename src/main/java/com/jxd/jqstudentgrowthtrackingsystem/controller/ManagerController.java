package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.Dept;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptEvaluatorService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IStudentService;
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

    @PostMapping("/getDepts")
    public Map<String, Object> getDepts(@RequestBody Map<String, String> listQuery) {
        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 10 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page"));
        String deptname = listQuery.get("filter") == null ? "" : listQuery.get("filter");
        Map<String,Object> map = new HashMap<>();
        map= deptService.getDeptByPage(deptname, limit, page);
        return map;
    }

    @PostMapping("/addOrUpdDept")
    public String addOrUpdDept(@RequestBody Dept dept) {
        boolean isAddOrUpd = deptService.saveOrUpdate(dept);
        if (isAddOrUpd) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/getDeptById/{deptno}")
    public Dept getEmpById(@PathVariable int deptno) {
        String a = "123";
        Dept dept = deptService.getById(deptno);
        return dept;
    }


    @PostMapping("/delDeptById")
    public String delDeptById(@RequestBody String deptno){
        String deptid = deptno.substring(0,deptno.length()-1);
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("deptno",deptid) ;

        int studentCountInDeptnos = studentService.count(wrapper);
        int evaluatorCountInDeptnos= deptEvaluatorService.count(wrapper);
        int count = studentCountInDeptnos + evaluatorCountInDeptnos;

        boolean isDel= false;
        if (count == 0){
            isDel =deptService.removeById(deptid);
        }

        if (isDel){
            return "success";
        }else {
            return "fail";
        }
    }


    @PostMapping("/delBatchDept")
    public String delBatchDept(@RequestBody String[] arrDeptnos){
        List<String>  deptnos = Arrays.asList(arrDeptnos);

        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.in("deptno",deptnos) ;
        int studentCountInDeptnos = studentService.count(wrapper);

        int evaluatorCountInDeptnos= deptEvaluatorService.count(wrapper);
        int count = studentCountInDeptnos + evaluatorCountInDeptnos;
        boolean isDel = false;
        if (count == 0){
            isDel = deptService.removeByIds(deptnos);
        }
        if (isDel){
            return "success";
        }else {
            return "fail";
        }
    }

}
