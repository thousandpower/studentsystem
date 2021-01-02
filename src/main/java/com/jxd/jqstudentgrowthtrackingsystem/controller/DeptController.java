package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.jxd.jqstudentgrowthtrackingsystem.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private IDeptService deptService;

    @PostMapping("/getDepts")
    public Map<String, Object> getDepts(@RequestBody Map<String, String> listQuery) {

        int limit = Integer.valueOf(listQuery.get("limit")) == null ? 3 : Integer.valueOf(listQuery.get("limit"));
        int page = Integer.valueOf(listQuery.get("page")) == null ? 1 : Integer.valueOf(listQuery.get("page"));
        String deptname = listQuery.get("filter") == null ? "" : listQuery.get("filter");
        Map<String,Object> map = new HashMap<>();
        map= deptService.getDeptByPage(deptname, limit, page);
        return map;


    }


}
