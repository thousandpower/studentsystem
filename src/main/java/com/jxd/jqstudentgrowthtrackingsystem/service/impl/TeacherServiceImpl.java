package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.ICourseScoreDao;
import com.jxd.jqstudentgrowthtrackingsystem.dao.ITeacherDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Evaluator;
import com.jxd.jqstudentgrowthtrackingsystem.model.Teacher;
import com.jxd.jqstudentgrowthtrackingsystem.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/4
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<ITeacherDao, Teacher> implements ITeacherService {
    @Autowired
    private ITeacherDao teacherDao;


    /**
     * @param
     * @Description: 分页查询所有在职教师信息
     * @Date: 2021/1/12
    */
    @Override
    public Map<String, Object> getTeacherByPage(String username, int limit, int page) {
        //构造分页对象
        Page<Map<String, Object>> pages = new Page<>(page, limit);
        Map<String, Object> map = new HashMap<>();

        //调用dao层获取数据
        IPage<Map<String, Object>> result = teacherDao.getTeacherByPage(pages,username);
        //查询出的教师信息 （分页）
        map.put("teacher",result.getRecords());
        //总条数
        map.put("total",result.getTotal());
        //总页数
        map.put("pageCount",result.getPages());

        return map;
    }

    /**
     * @param
     * @Description: 批量删除教师，假删除，将状态置为0
     * @Date: 2021/1/12
    */
    @Override
    public boolean delTeacherByBatch(String[] teacherids) {
        return teacherDao.delTeacherByBatch(teacherids);
    }


    /**
     * @param
     * @Description: 按行删除教师，假删除，将状态置为0
     * @Date: 2021/1/12
    */
    @Override
    public boolean delTeacherById(int teacherid) {
        return teacherDao.delTeacherById(teacherid);
    }


    /**
     * @param
     * @Description: 分页查询所有当前班期下的学生
     * @Date: 2021/1/12
    */
    @Override
    public Map<String, Object> getStudentByPage(String username, int limit, int page, int gradeid) {
        Page<Map<String, Object>> pages = new Page<>(page, limit);
        Map<String, Object> map = new HashMap<>();

        //调用dao层获取数据
        IPage<Map<String, Object>> result = teacherDao.getStudentByPage(pages,username,gradeid);
        //查询出的学生信息 （分页）
        map.put("students",result.getRecords());
        //总条数
        map.put("total",result.getTotal());
        //总页数
        map.put("pageCount",result.getPages());

        return map;
    }

    /**
     * @param
     * @Description: 获取选中学生的评价信息
     * @Date: 2021/1/12
    */
    @Override
    public Map<String, Object> getEvaluationBysId(int sId) {
        return teacherDao.getEvaluationBysId(sId);
    }


    /**
     * @param
     * @Description: 分页查询总评成绩在3分以上的某一班期学生
     * @Date: 2021/1/12
    */
    @Override
    public Map<String, Object> getAssignList(String username, int limit, int page, int gradeid) {
        Page<Map<String, Object>> pages = new Page<>(page, limit);
        Map<String, Object> map = new HashMap<>();

        //调用dao层获取数据
        IPage<Map<String, Object>> result = teacherDao.getAssignList(pages,username,gradeid);
        //查询出的学生信息 （分页）
        map.put("students",result.getRecords());
        //总条数
        map.put("total",result.getTotal());
        //总页数
        map.put("pageCount",result.getPages());

        return map;
    }

    /**
     * @param
     * @Description: 为学生分配部门，分配职位，添加入职日期
     * @Date: 2021/1/12
    */
    @Override
    public boolean assignStudent(int studnetid, String hiredate, int jobid, int deptno) {
        return teacherDao.assignStudent(studnetid,hiredate,jobid,deptno);
    }
}
