package com.jxd.jqstudentgrowthtrackingsystem.service;

import java.util.Map;

/**
 * @ClassName: IDeptService
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:47
 */
public interface IDeptService {

    public Map<String,Object> getDeptByPage(String deptname,int limit,int page);

}
