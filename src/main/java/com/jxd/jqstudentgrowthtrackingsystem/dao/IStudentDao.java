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
     * 查询学生id
     *
     * @param student 学员对象
     * @return
     */
    Student selectId(Student student);

    /**
     * 删除学员
     * @param studentids
     * @return
     */
    boolean deleteAnyStudents(Integer[] studentids);
}
