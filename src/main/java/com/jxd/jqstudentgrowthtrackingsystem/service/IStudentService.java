package com.jxd.jqstudentgrowthtrackingsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxd.jqstudentgrowthtrackingsystem.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @ClassName: IStudentService
 * @Author: fws
 * @Description:
 * @Date: 2020/12/31 14:48
 */
public interface IStudentService extends IService<Student> {
    /**
     * 查询学生id
     *
     * @param student 学员对象
     * @return
     */
    Student selectId(Student student);

    /**
     * 删除学员
     *
     * @param studentids
     * @return
     */
    boolean removeAnyStudents(Integer[] studentids);

    /**
     * 学员培训学校评价
     *
     * @param studentid
     * @return
     */
    Map<String, Object> selectThisStudentSchoolEvaluation(Integer studentid);

    /**
     * 学员转正及工作1-3年的评价
     *
     * @param studentid
     * @param workYear
     * @return
     */
    Map<String, Object> selectThisStudentDeptEvaluation(Integer studentid, int workYear);
    /**
     * 查询全部用户
     * @param limit
     * @param page
     * @param studentName
     * @return
     */
    Map<String, Object> getAllstudent(int limit, int page, String studentName,String gradeid);
}
