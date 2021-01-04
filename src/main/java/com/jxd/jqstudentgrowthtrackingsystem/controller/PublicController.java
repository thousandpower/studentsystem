package com.jxd.jqstudentgrowthtrackingsystem.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxd.jqstudentgrowthtrackingsystem.config.UploadPhotoConfig;
import com.jxd.jqstudentgrowthtrackingsystem.model.UserLogin;
import com.jxd.jqstudentgrowthtrackingsystem.service.IMenuService;
import com.jxd.jqstudentgrowthtrackingsystem.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
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
        userLogin = userLoginService.selectWithLogin(userLogin.getUserid(), userLogin.getPassword());
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
     * 查询用户信息
     * @param id 用户id
     * @return
     */
    @RequestMapping("/getThisUser")
    public Map<String, Object> getThisUser(Integer id){
        UserLogin userLogin = userLoginService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("data",userLogin);
        map.put("status", 200);
        return map;
    }

    /**
     * 修改或重置密码
     * @param userLogin 用户对象
     * @return
     */
    @RequestMapping("/editPwd")
    public String editPwd(@RequestBody UserLogin userLogin) {
        //对前端传递过来的数据中是否含有密码进行判断
        if (userLogin.getPassword().length() == 0){
            userLogin.setPassword("a123456");
        }
        boolean flag = userLoginService.updateById(userLogin);
        if (flag) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 头像上传
     * @param file 头像文件
     * @return
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        //处理文件名 添加UUID 保证每个文件名全局唯一
        //获取原文件名
        String fileName = file.getOriginalFilename();
        //获取UUID 全局唯一的 32位字符串 包含数字字母和-
        String uuid = UUID.randomUUID().toString();
        String new_fileName = uuid + "_" + fileName;
        //将文件存到服务器上
        String path = UploadPhotoConfig.getPath();
        File file_final = new File(path,new_fileName);
        //判断文件所在文件夹是否存在
        if (!file_final.getParentFile().exists()){
            file_final.getParentFile().mkdir();
        }
        file.transferTo(file_final);
        String photoPath = path+"\\"+new_fileName;
        return photoPath;
    }
}
