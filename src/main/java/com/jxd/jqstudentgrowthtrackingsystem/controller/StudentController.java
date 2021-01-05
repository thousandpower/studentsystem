package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.jxd.jqstudentgrowthtrackingsystem.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/getMyInform")
    public Map<String,Object> getMyInform(Integer userid){
        Map<String,Object> map = new HashMap<>();
        map.put("data",studentService.getById(userid));
        map.put("status",200);
       return map;
    }
}
