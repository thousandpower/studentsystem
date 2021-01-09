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
     * 按部门号分页模糊查询学生
     * @param pages 分页信息
     * @param studentname 学生姓名 （模糊查询）
     * @param deptno 部门编号
     * @return 封在IPage中的学生信息集合
     */
    IPage<Map<String,Object>> getStudentByPageAndDeptno(Page<Map<String,Object>> pages,  @Param("studentName")String studentname, @Param("deptno")int deptno);

    /**
     * 通过id获取学生信息（联表获取部门名称和职务名称）
     * @param studentid 学生编号
     * @return 学生信息集合
     */
    Map<String, Object> getStudentById(int studentid);

}
