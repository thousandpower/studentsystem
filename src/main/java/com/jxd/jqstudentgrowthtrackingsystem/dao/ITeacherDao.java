package com.jxd.jqstudentgrowthtrackingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxd.jqstudentgrowthtrackingsystem.model.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author: lrc
 * @Description:
 * @Date: 2021/1/4
 */
public interface ITeacherDao extends BaseMapper<Teacher> {
    IPage<Map<String,Object>> getTeacherByPage(Page<Map<String,Object>> pages, @Param("username")String username);

    boolean delTeacherByBatch(String[] teachers);

    boolean delTeacherById(int teacherid);

    IPage<Map<String,Object>> getStudentByPage(Page<Map<String,Object>> pages,
                                               @Param("username")String username,
                                               @Param("gradeid")int gradeid);

    Map<String,Object> getEvaluationBysId(int sId);

    IPage<Map<String,Object>> getAssignList(Page<Map<String,Object>> pages,
                                               @Param("username")String username,
                                               @Param("gradeid")int gradeid);

    boolean assignStudent(@Param("studentid") int studentid,
                          @Param("hiredate") String hiredate,
                          @Param("jobid") int jobid,
                          @Param("deptno") int deptno);
}
