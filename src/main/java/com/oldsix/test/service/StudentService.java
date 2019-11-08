package com.oldsix.test.service;

import com.oldsix.test.datashow.StudentShow;
import com.oldsix.test.datasource.dto.Student;
import com.oldsix.test.datasource.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 通过学号查询学生
     * @param empId
     * @return
     */
    public int findUserByEmpid(String empId){
        return studentMapper.selectByEmpId(empId);
    }

    /**
     * 添加学生
     * @param student
     */
    @Transactional
    public void addUser(Student student) {
        studentMapper.saveStudentInfo(student);
    }

    /**
     * 展示所有学生
     * @return
     */
    public List<StudentShow> showAllStudent() {
        return studentMapper.selectStudentsNoCondition();
    }

    /**
     * 查询学生的类别和总学分
     * @param empId
     * @return
     */
    public Student findScoreByEmpid(String empId) {
        return studentMapper.selectStudentByEmpId(empId);
    }

    /**
     * 增加学生课程关系映射
     * @param courseid
     * @param userId
     */
    public void addCourseStudentReflect(String courseid, Integer userId) {
        studentMapper.insertStudentCourseMap(new BigDecimal(courseid).intValue(),userId);
    }

    /**
     * 更新课程选择人数
     * @param courseid
     */
    public void updateCourseStudentAccout(String courseid) {
        studentMapper.updateCourseNumberAccout(new BigDecimal(courseid).intValue());
    }

    /**
     * 更新学生总学分
     * @param courseid
     * @param userId
     */
    public void updateStudentScore(String courseid, Integer userId) {
        studentMapper.updateStudentTotalScore(new BigDecimal(courseid).intValue() , userId);
    }

    /**
     * 修改学生总学分
     * @param courseid
     * @param userId
     */
    public void subStudentScore(String courseid, Integer userId) {
        studentMapper.subStudentTotalScore(new BigDecimal(courseid).intValue(),userId);
    }

    /**
     * 修改课程选择人数
     * @param courseid
     */
    public void subCourseStudentAccout(String courseid) {
        studentMapper.subCourseStudentNumber(new BigDecimal(courseid).intValue());
    }

    /**
     * 修改学生课程关系映射
     * @param courseid
     * @param userId
     */
    public void subCourseStudentReflect(String courseid, Integer userId) {
        studentMapper.subReflectWithStudentAndCourse(new BigDecimal(courseid).intValue(),userId);
    }

    /**
     * 更新学生信息
     * @param student
     */
    public void updateStudentInfoById(Student student) {
        studentMapper.updateInfoByPrimary(student);
    }

    /**
     * 查询学生是否存在
     * @param empId
     * @return
     */
    public int findUserCount(String empId) {
        return studentMapper.selectUserByEmpId(empId);
    }
}
