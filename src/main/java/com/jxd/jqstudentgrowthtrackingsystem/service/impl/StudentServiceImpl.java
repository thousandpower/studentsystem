package com.jxd.jqstudentgrowthtrackingsystem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.jqstudentgrowthtrackingsystem.dao.IStudentDao;
import com.jxd.jqstudentgrowthtrackingsystem.model.Student;
import com.jxd.jqstudentgrowthtrackingsystem.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: IStudentService
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:50
 */
@Service
public class StudentServiceImpl extends ServiceImpl<IStudentDao, Student> implements IStudentService {
    @Autowired
    private IStudentDao studentDao;

    @Override
    public Student selectId(Student student) {
        return studentDao.selectId(student);
    }

    @Override
    public boolean removeAnyStudents(Integer[] studentids) {
        return studentDao.deleteAnyStudents(studentids);
    }


    @Override
    public  Map<String, Object> getStudentByPageAndDeptno(String studentname, int deptno,int limit, int page){
        //构造分页对象
        Page<Map<String, Object>> pages = new Page<>(page, limit);
        Map<String, Object> map = new HashMap<>();
        //调用dao层获取数据
        IPage<Map<String, Object>> result =studentDao.getStudentByPageAndDeptno(pages, studentname,deptno);
        //查询出的员工信息 （分页）
        map.put("students",result.getRecords());
        //总条数
        map.put("total",result.getTotal());
        //总页数
        map.put("pageCount",result.getPages());

        return map;


    }


}
