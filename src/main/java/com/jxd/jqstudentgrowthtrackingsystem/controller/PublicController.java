package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import com.jxd.jqstudentgrowthtrackingsystem.service.IMenuService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
     * fws
     *
     * @return map集合 封装的前台所需的各种数据
     */
    @RequestMapping("/getAllMenu")
    public Map<String, Object> getAllMenu(@RequestBody String role) {
        String authority = role.substring(0, role.length() - 1);
        Map<String, Object> map = new HashMap<>();

        //条件查询
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("authority", authority);
        wrapper.or();
        //公共权限
        wrapper.eq("authority", 4);

        map.put("data", menuService.list(wrapper));
        map.put("status", 200);
        return map;
    }

    /**
     * 用户登录的验证
     * lt
     *
     * @param userLogin 接收的是用户对象
     * @return
     */
    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody UserLogin userLogin) {
        userLogin = userLoginService.selectWithLogin(userLogin.getUserid(), userLogin.getPassword());
        Map<String, Object> map = new HashMap<>();
        String roleName = "";
        if (userLogin != null) {
            map.put("data", userLogin);

            switch (userLogin.getRole()) {
                case 0:
                    roleName = "管理员";
                    break;
                case 1:
                    roleName = "教师";
                    break;
                case 2:
                    roleName = "项目部评价人";
                    break;
                case 3:
                    roleName = "学员";
                    break;
            }
            map.put("roleName", roleName);
            map.put("status", "200");
        } else {
            map.put("status", "500");
        }
        return map;
    }

    /**
     * 查询用户信息
     * lt
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping("/getThisUser/{id}")
    public Map<String, Object> getThisUser(@PathVariable Integer id) {
        UserLogin userLogin = userLoginService.selectThisUser(id);
        Map<String, Object> map = new HashMap<>();
        map.put("data", userLogin);
        map.put("status", 200);
        return map;
    }

    /**
     * 修改密码
     * lt
     *
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

    /**
     * 重置密码
     *
     * @param userid 用户编号
     * @return 是否成功的标志
     */
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
     * lt
     *
     * @param userid
     * @return
     */
    @RequestMapping("/getMyPassword/{userid}")
    public Map<String, Object> getMyPassword(@PathVariable Integer userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userLoginService.getMyPassword(userid).getPassword());
        return map;
    }

    /**
     * 上传
     *
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        // 文件存储位置，文件的目录要存在才行，可以先创建文件目录，然后进行存储
        String filePath = "D:\\photo";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //文件存储
        //上传文件项
        //获取上传文件的名称
        String filename = multipartFile.getOriginalFilename();
        System.out.println("filename:" + filename);
        //把文件名称设置唯一值
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "-" + filename;
        System.out.println("filename:" + filename);
        //完成上传文件
        File newFile = new File(filePath, filename);
        multipartFile.transferTo(newFile);
        // replaceAll 用来替换windows中的\\ 为 /
        System.out.println(filename);
        System.out.println(newFile.getAbsolutePath().replaceAll("\\\\", "/"));
        //返回文件名 前台通过固定地址+文件名的方法访问该图片 存储使用的是相对路径
        return filename;
    }
}
