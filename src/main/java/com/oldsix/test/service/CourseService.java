package com.oldsix.test.service;

import com.oldsix.test.datashow.CourseShow;
import com.oldsix.test.datashow.StudentShow;
import com.oldsix.test.datasource.dto.Course;
import com.oldsix.test.datasource.mapper.CourseMapper;
import com.oldsix.test.datasource.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 插入课程
     * @param course
     */
    @Transactional
    public void insertCourse(Course course){
        courseMapper.insertCourse(course);
    }

    /**
     * 查询所有课程
     * @return
     */
    public List<CourseShow> selectAllCourse() {
        return courseMapper.selectAllCourseNoCondition();
    }

    /**
     * 根据课程查询学生
     * @param courseId
     * @return
     */
    public List<StudentShow> selectStudentByCourseId(Long courseId){
        return courseMapper.selectStudentByCourseId(courseId);
    }

    /**
     * 根据主键查询课程
     * @param courseID
     * @return
     */
    public CourseShow selectCourseById(long courseID) {
        return courseMapper.selectCourseByPrimaryKey(courseID);
    }

    /**
     * 查询学生所有课程
     * @param studentId
     * @return
     */
    public List<CourseShow> selectAllCourseByStudentId(Integer studentId) {
        return studentMapper.selectCourseByStudentId(studentId);
    }

    /**
     * 查询学生未选课程
     * @param userId
     * @return
     */
    public List<CourseShow> selectAllNoChooseCourse(int userId) {
        return studentMapper.selectAllCourseNoSelected(userId);
    }

    /**
     * 更新学生总学分
     * @param scoreDifferents
     * @param id
     */
    @Transactional
    public void updateStudentScore(double scoreDifferents, Long id) {
        studentMapper.updateScoreOfCourseChange(scoreDifferents, id);
    }

    /**
     * 更新课程信息
     * @param course
     */
    @Transactional
    public void updateCourse(Course course) {
        courseMapper.updateCourseById(course);
    }
}
