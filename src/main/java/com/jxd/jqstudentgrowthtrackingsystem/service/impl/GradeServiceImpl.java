package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IGradeDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Grade;
import com.jxd.jqstudentgrowthtrackingsystem.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/4
import java.util.List;

/**
 * @ClassName GradeServiceImpl
 * @Description TODO
 * @Author liutong
 * @Date 2021/1/4 9:00
 * @Version 1.0
 */
@Service
public class GradeServiceImpl extends ServiceImpl<IGradeDao, Grade> implements IGradeService {
    @Autowired
    private IGradeDao gradeDao;

    /**
     * @param
     * @Description: 分页查询所有班期信息
     * @Date: 2021/1/12
    */
    @Override
    public Map<String, Object> getGradeByPage(int limit, int page) {
        //构造分页对象
        Page<Map<String, Object>> pages = new Page<>(page, limit);
        Map<String, Object> map = new HashMap<>();

        //调用dao层获取数据
        IPage<Map<String, Object>> result = gradeDao.getGradeByPage(pages);
        //查询出的员工信息 （分页）
        map.put("grade",result.getRecords());
        //总条数
        map.put("total",result.getTotal());
        //总页数
        map.put("pageCount",result.getPages());

        return map;
    }

    /**
     * @param
     * @Description: 查询对应的班期
     * @Date: 2021/1/12
    */
    public Map<String, Object> selectGrade(String gradeid, int limit, int page) {
        //构造分页对象
        Page<Map<String, Object>> pages = new Page<>(page, limit);
        Map<String, Object> map = new HashMap<>();

        //调用dao层获取数据
        IPage<Map<String, Object>> result = gradeDao.selectGrade(pages,gradeid);
        //查询出的班期信息 （分页）
        map.put("grade",result.getRecords());
        //总条数
        map.put("total",result.getTotal());
        //总页数
        map.put("pageCount",result.getPages());

        return map;
    }

    /**
     * @param
     * @Description: 根据教师id查询教师所教授的所有班期
     * @Date: 2021/1/12
    */
    @Override
    public List<Grade> getGradeByTId(int tid) {
        return gradeDao.getGradeByTId(tid);
    @Override
    public List<Grade> selectAllGrade() {
        return gradeDao.selectAllGrade();
    }
}
