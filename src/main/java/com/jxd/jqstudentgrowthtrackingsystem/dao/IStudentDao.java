package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxd.jqstudentgrowthtrackingsystem.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @ClassName: IStudentDao
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:46
 */
public interface IStudentDao extends BaseMapper<Student> {
    /**
     * 查询全部学员
     * @param page 分页对象
     * @param studentName 模糊查询
     * @return
     */
    IPage<Map<String, Object>> selectAllstudents(Page<Map<String,Object>> page,
                                                 @Param("studentName") String studentName);
    /**
     * 查询学生id
     * @param student 学员对象
     * @return
     */
    Student selectId(Student student);
}
