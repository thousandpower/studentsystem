package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import com.jxd.jqstudentgrowthtrackingsystem.service.IMenuService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    public Map<String, Object> getAllMenu(UserLogin userLogin) {
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
        userLogin = userLoginService.selectWithLogin(userLogin.getUserid(), userLogin.getPassword());
        Map<String, Object> map = new HashMap<>();
        if (userLogin != null) {
            map.put("data", userLogin);
            map.put("status", "200");
        } else {
            map.put("status", "500");
        }
        return map;
    }

    /**
     * 查询用户信息
     * @param id 用户id
     * @return
     */
    @RequestMapping("/getThisUser/{id}")
    public Map<String, Object> getThisUser(@PathVariable Integer id){
        UserLogin userLogin = userLoginService.selectThisUser(id);
        Map<String, Object> map = new HashMap<>();
        map.put("data",userLogin);
        map.put("status", 200);
        return map;
    }

    /**
     * 修改密码
     * @param userLogin 用户对象
     * @return
     */
    @RequestMapping("/editMyPwd")
    public String editPwd(@RequestBody UserLogin userLogin) {

        boolean editUserFlag = userLoginService.updateThisUserPwd(userLogin);
        if (editUserFlag) {
            return "success";
        } else {
            return "fail";
        }
    }
    @RequestMapping("/resetMyPwd/{userid}")
    public String resetPwd(@PathVariable Integer userid) {
        boolean resetUserFlag = userLoginService.resetThisUserPwd(userid);
        if (resetUserFlag) {
            return "success";
        } else {
            return "fail";
        }
    }
    /**
     * 查询用户的密码
     * @param userid
     * @return
     */
    @RequestMapping("/getMyPassword/{userid}")
    public Map<String,Object> getMyPassword(@PathVariable Integer userid){
        Map<String,Object> map = new HashMap<>();
        map.put("data",userLoginService.getMyPassword(userid).getPassword());
        return map;
    }
}
