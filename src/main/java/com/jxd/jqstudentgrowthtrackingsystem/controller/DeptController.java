package com.jxd.jqstudentgrowthtrackingsystem.controller;


import com.jxd.jqstudentgrowthtrackingsystem.model.DeptEvaluator;
import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptEvaluatorService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DeptController
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:43
 */
@RestController
public class DeptController {
    @Autowired
    private IDeptEvaluatorService deptEvaluatorService;
    @Autowired
    private IStudentService studentService;

    @PostMapping("/getStudentsByDeptno")
    public Map<String, Object> getStudentsByDeptno(@RequestBody Map<String, String> listQuery) {
        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 9 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page"));
        String studentname = listQuery.get("filter") == null ? "" : listQuery.get("filter");
        int evaluatorid = Integer.valueOf(listQuery.get("evaluatorid"));
        DeptEvaluator deptEvaluator = new DeptEvaluator();
        deptEvaluator = deptEvaluatorService.getById(evaluatorid);
        int deptno = deptEvaluator.getDeptno();

        Map<String, Object> map = new HashMap<>();
        map = studentService.getStudentByPageAndDeptno(studentname,deptno, limit, page);
        return map;
    }


}
