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
     *  lt
     * @param studentids
     * @return
     */
    boolean removeAnyStudents(Integer[] studentids);

    /**
     * 按部门号分页模糊查询学生
     *  fws
     * @param studentname 学生姓名 （模糊查询）
     * @param deptno      部门编号
     * @param limit       每页条数
     * @param page        页码
     * @return 学生信息
     */
    Map<String, Object> getStudentByPageAndDeptno(String studentname, int deptno, int limit, int page);

    /**
     * 通过id获取学生信息（联表获取部门名称和职务名称）
     *  fws
     * @param studentid 学生编号
     * @return 学生信息集合
     */
    Map<String, Object> getStudentById(int studentid);

}
