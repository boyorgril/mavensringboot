package com.oldsix.test.controller;

import com.oldsix.test.datashow.CourseShow;
import com.oldsix.test.datashow.StudentShow;
import com.oldsix.test.datasource.dto.Student;
import com.oldsix.test.service.CourseService;
import com.oldsix.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 学生控制器
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");

    /**
     * 保存学生信息
     * @param httpServletRequest
     * @param empId
     * @param name
     * @param sex
     * @param birthday
     * @param status
     * @param grade
     * @param profession
     * @param myclass
     * @param teacherName
     * @param tutor
     * @param searchDirection
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping("/savePersonalInfo")
    public String saveUserInfo(HttpServletRequest httpServletRequest,@RequestParam("empId") String empId,@RequestParam("name") String name,
                                    @RequestParam("sex") String sex, @RequestParam("birthday") String birthday , @RequestParam("status") String status ,
                                            @RequestParam("grade") String grade ,@RequestParam("profession") String profession ,
                                                    @RequestParam("myclass") String myclass, @RequestParam("teacherName") String teacherName,
                                                            @RequestParam("tutor") String tutor, @RequestParam("searchDirection") String searchDirection, Model model) throws ParseException {

        Student student = new Student();
        System.out.println((Integer) httpServletRequest.getSession().getAttribute("userId"));
        student.setEmpId(empId);
        student.setName(name);
        student.setBirthday(sdf.parse(birthday.substring(0,11)));
        student.setSex(sex);
        student.setGrade(grade);
        student.setProfession(profession);
        student.setMyclass(myclass);
        student.setTeacherName(teacherName);
        student.setTutor(tutor);
        student.setStatus(status);
        student.setSearchDirection(searchDirection);
        studentService.addUser(student);
        List<StudentShow> allStudents = studentService.showAllStudent();
        model.addAttribute("studentInfo",allStudents);
        return "student/showAllStudent";
    }

    /**
     * 展示所有学生
     * @param model
     * @return
     */
    @RequestMapping("/showAllStudent")
    public String showAllUser(Model model){
       List<StudentShow> allStudents = studentService.showAllStudent();
       model.addAttribute("studentInfo",allStudents);
        return "student/showAllStudent";
    }

    /**
     * 选择学习该课程
     * @param courseId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/chooseCourse")
    public String chooseCourse(String courseId,HttpServletRequest request,
                               Model model){
        Integer userId =  (Integer) request.getSession().getAttribute("userId");
        if(!StringUtils.isEmpty(courseId)){

            String[] couridArr = courseId.split(",");
            for(String courseid : couridArr){
                studentService.addCourseStudentReflect(courseid , userId);
                studentService.updateCourseStudentAccout(courseid);
                studentService.updateStudentScore(courseid,userId);
            }
        }
        List<CourseShow> allCourse = courseService.selectAllCourseByStudentId(userId);
        model.addAttribute("allCourse",allCourse);
        return "student/personalCourse";
    }

    /**
     * 未选修的课程
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("noChooseCourse")
    public String showNoChooseCourse(HttpServletRequest request, Model model){
        Integer userId =  (Integer) request.getSession().getAttribute("userId");
        List<CourseShow> allCourse = courseService.selectAllNoChooseCourse(userId);
        model.addAttribute("allCourse",allCourse);
        return "student/info";
    }

    /**
     * 删除已选课程
     * @param courseId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("deleteChooseCourse")
    public String deleteCourse(String courseId,HttpServletRequest request,
                               Model model){
        Integer userId =  (Integer) request.getSession().getAttribute("userId");
        if(!StringUtils.isEmpty(courseId)){

            String[] couridArr = courseId.split(",");
            for(String courseid : couridArr){
                studentService.subCourseStudentReflect(courseid , userId);
                studentService.subCourseStudentAccout(courseid);
                studentService.subStudentScore(courseid,userId);
            }
        }
        List<CourseShow> allCourse = courseService.selectAllCourseByStudentId(userId);
        model.addAttribute("allCourse",allCourse);
        return "student/personalCourse";
    }

    /**
     * 该学生所选课程
     * @param studentId
     * @param model
     * @return
     */
    @RequestMapping("courseOfStudent")
    public String studensCourse(@RequestParam("studentId") String studentId, Model model){
        List<CourseShow> allCourse = courseService.selectAllCourseByStudentId(new BigDecimal(studentId).intValue());
        model.addAttribute("allCourse",allCourse);
        return "student/personalCourse";
    }

    /**
     * 更新课程信息
     * @param empId
     * @param name
     * @param sex
     * @param studentid
     * @param grade
     * @param profession
     * @param myclass
     * @param teacherName
     * @param tutor
     * @param searchDirection
     */
    @RequestMapping("updateCourseInfo")
    @ResponseBody
    public void updateStudenInfo(@RequestParam("empId") String empId,@RequestParam("name") String name,
                                    @RequestParam("sex") String sex, @RequestParam("id") String studentid,
                                            @RequestParam("grade") String grade ,@RequestParam("profession") String profession ,
                                                @RequestParam("myclass") String myclass, @RequestParam("teacherName") String teacherName,
                                                    @RequestParam("tutor") String tutor, @RequestParam("searchDirection") String searchDirection){
        Student student = new Student();
        student.setId(new BigDecimal(studentid).intValue());
        student.setName(name);
        student.setEmpId(empId);
        student.setSex(sex);
        student.setGrade(grade);
        student.setProfession(profession);
        student.setTutor(tutor);
        student.setSearchDirection(searchDirection);
        student.setMyclass(myclass);
        student.setTeacherName(teacherName);
        studentService.updateStudentInfoById(student);
    }

}
