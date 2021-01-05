package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IMenuDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Menu;
import com.jxd.jqstudentgrowthtrackingsystem.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: MenuServiceImpl
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 17:35
 */

@Service
public class MenuServiceImpl extends ServiceImpl<IMenuDao, Menu> implements IMenuService {

}
