package com.oldsix.test.controller;

import com.oldsix.test.datashow.CourseShow;
import com.oldsix.test.datashow.StudentShow;
import com.oldsix.test.datasource.dto.Course;
import com.oldsix.test.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 课程控制器
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 保存课程信息
     * @param coursename
     * @param coursenumber
     * @param teacher
     * @param introduction
     * @param score
     * @return
     */
    @RequestMapping("/saveCourseInfo")
    public String saveCourseInfo(@RequestParam("coursename") String coursename, @RequestParam("coursenumber") String coursenumber,
                                 @RequestParam("teacher") String teacher, @RequestParam("introduction") String introduction ,
                                 @RequestParam("score") String score){

        Course course = new Course();
        course.setCourseName(coursename);
        course.setCourseNumber(coursenumber);
        course.setTeacher(teacher);
        course.setIntroduction(introduction);
        course.setNumberOfStudent(0);
        course.setScore(new BigDecimal(score).doubleValue());
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        courseService.insertCourse(course);
        List<CourseShow> allCourse = courseService.selectAllCourse();
        return "course/showAllCourse";
    }

    /**
     * 展示所有课程
     * @param model
     * @return
     */
    @RequestMapping("/showAllCourse")
    public String showCourseInfo(Model model){
        List<CourseShow> allCourse = courseService.selectAllCourse();
        model.addAttribute("allCourse",allCourse);
        return "course/showAllCourse";
    }

    /**
     * 展示所选该课程的学生
     * @param courseId
     * @param model
     * @return
     */
    @RequestMapping("/studentOfCourse")
    public String showStudentOfCourse(@RequestParam("courseId") String courseId, Model model){
        List<StudentShow> allCourseStudent = courseService.selectStudentByCourseId(new BigDecimal(courseId).longValue());
        CourseShow courseShow = courseService.selectCourseById(new BigDecimal(courseId).longValue());
        model.addAttribute("AllStudent",allCourseStudent);
        model.addAttribute("courseInfo",courseShow);
        return "course/studentofcourse";
    }

    /**
     *更新课程信息
     * @param coursename
     * @param coursenumber
     * @param teacher
     * @param introduction
     * @param score
     * @param courseid
     * @param oldscore
     */
    @RequestMapping("/updateCourseInfo")
    @ResponseBody
    public void updateCourseInfo(@RequestParam("coursename") String coursename, @RequestParam("coursenumber") String coursenumber,
                                        @RequestParam("teacher") String teacher, @RequestParam("introduction") String introduction ,
                                            @RequestParam("score") String score, @RequestParam("courseid") String courseid,  @RequestParam("oldscore") String oldscore){

        Course course = new Course();
        Long id = new BigDecimal(courseid).longValue();
        course.setId(id);
        course.setCourseName(coursename);
        course.setCourseNumber(coursenumber);
        course.setTeacher(teacher);
        course.setIntroduction(introduction);
        course.setScore(new BigDecimal(score).doubleValue());
        double scoreDifferents = (new BigDecimal(score).subtract(new BigDecimal(oldscore))).doubleValue();
        courseService.updateCourse(course);
        courseService.updateStudentScore(scoreDifferents, id);
    }
}
