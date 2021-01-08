package com.jxd.jqstudentgrowthtrackingsystem.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName Student
 * @Description TODO
 * @Author liutong
 * @Date 2020/12/31 9:28
 * @Version 1.0
 */
@TableName("student")
public class Student {
    //学生id
    @TableId("studentid")
    private int studentid;

    //学生姓名
    @TableField("student_name")
    private String studentName;
    //性别
    private int sex;
    //民族
    private String nation;
    //出生日期
    private String birthday;
    //籍贯
    @TableField("native_place")
    private String nativePlace;
    //婚姻状态 0未婚 1已婚 2丧偶 3离婚
    @TableField("marital_status")
    private int maritalStatus;
    //电话号码
    private String phone;
    //身份证号
    @TableField("id_number")
    private String idNumber;
    //入职日期
    private String hiredate;
    //毕业院校
    private String college;
    //专业
    private String major;
    //照片路径
    @TableField("images_directory")
    private String imagesDirectory;
    //部门编号
    private int deptno;
    //班期
    private int gradeid;
    //是否离职
    private int flag;
    //备注
    private String remarks;

    /*职业id*/
    private int jobid;

    public Student(int studentid, String studentName, int sex, String nation, String birthday, String nativePlace, int maritalStatus, String phone, String idNumber, String hiredate, String college, String major, String imagesDirectory, int deptno, int gradeid, int flag, String remarks, int jobid) {
        this.studentid = studentid;
        this.studentName = studentName;
        this.sex = sex;
        this.nation = nation;
        this.birthday = birthday;
        this.nativePlace = nativePlace;
        this.maritalStatus = maritalStatus;
        this.phone = phone;
        this.idNumber = idNumber;
        this.hiredate = hiredate;
        this.college = college;
        this.major = major;
        this.imagesDirectory = imagesDirectory;
        this.deptno = deptno;
        this.gradeid = gradeid;
        this.flag = flag;
        this.remarks = remarks;
        this.jobid = jobid;
    }

    public Student() {
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getImagesDirectory() {
        return imagesDirectory;
    }

    public void setImagesDirectory(String imagesDirectory) {
        this.imagesDirectory = imagesDirectory;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public int getGradeid() {
        return gradeid;
    }

    public void setGradeid(int gradeid) {
        this.gradeid = gradeid;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }
}
