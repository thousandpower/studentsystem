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
    /**
     * 查询全部用户
     * @param page
     * @param studentName
     * @return
     */
    IPage<Map<String, Object>> selectAllStudent(Page<Map<String,Object>> page,
                                             @Param("studentName") String studentName);

    /**
     * 学员培训学校评价
     * @param studentid
     * @return
     */
    Map<String,Object> selectThisStudentSchoolEvaluation(@Param("studentid") Integer studentid);

    /**
     * 学员转正及工作1-3年的评价
     * @param studentid
     * @param workYear
     * @return
     */
    Map<String,Object> selectThisStudentDeptEvaluation(@Param("studentid") Integer studentid,
                                                       @Param("workYear") int workYear);
}
