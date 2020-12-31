package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: PublicController
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:44
 */
@RestController
public class PublicController {
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/getAllMenu")
    public Map<String,Object> getAllMenu(){
        Map<String,Object> map = new HashMap<>();

        //条件查询
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("authority",0);
        wrapper.or();
        wrapper.eq("authority",4);

        map.put("data",menuService.list(wrapper));
        map.put("status",200);
        return map;
    }



}
