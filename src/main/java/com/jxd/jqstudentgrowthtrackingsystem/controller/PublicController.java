package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import com.jxd.jqstudentgrowthtrackingsystem.service.IMenuService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private IUserLoginService userLoginService;

    /**
     * 获取各个身份对应的导航栏菜单
     *
     * @return map集合 封装的前台所需的各种数据
     */
    @RequestMapping("/getAllMenu")
    public Map<String, Object> getAllMenu() {
        Map<String, Object> map = new HashMap<>();

        //条件查询
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("authority", 0);
        wrapper.or();
        wrapper.eq("authority", 4);

        map.put("data", menuService.list(wrapper));
        map.put("status", 200);
        return map;
    }

    /**
     * 用户登录的验证
     *
     * @param userLogin 接收的是用户对象
     * @return
     */
    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody UserLogin userLogin) {
        userLogin = userLoginService.selectWithLogin(userLogin.getUsername(), userLogin.getPassword());
        Map<String, Object> map = new HashMap<>();
        if (userLogin != null) {
            map.put("data", userLogin);
            map.put("status", 200);
        } else {
            map.put("status", 500);
        }
        return map;
    }

    /**
     * 保存前端提交的数据
     * @param userLogin
     * @return
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(@RequestBody UserLogin userLogin) {
        boolean flag = userLoginService.updateById(userLogin);
        if (flag) {
            return "success";
        } else {
            return "fail";
        }
    }
}
