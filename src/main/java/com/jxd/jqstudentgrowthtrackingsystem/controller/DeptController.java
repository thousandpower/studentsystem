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
    public Map<String, Object> getDepts(@RequestBody Map<String, String> queryMap) {

        int limit = Integer.getInteger(queryMap.get("limit")) == null ? 10 : Integer.getInteger(queryMap.get("limit"));
        int page = Integer.getInteger(queryMap.get("page")) == null ? 1 : Integer.getInteger(queryMap.get("page"));
        String deptname = queryMap.get("filter") == null ? "" : queryMap.get("filter");
        Map<String,Object> map = new HashMap<>();
        map= deptService.getDeptByPage(deptname, limit, page);
        return map;


    }


}
