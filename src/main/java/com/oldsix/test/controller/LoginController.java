package com.oldsix.test.controller;


import com.oldsix.test.datashow.CourseShow;
import com.oldsix.test.datasource.dto.Student;
import com.oldsix.test.service.CourseService;
import com.oldsix.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    /**
     * 登录页
     * @return
     */
    @RequestMapping("/index")
    public String logSystem(){
        return "index";
    }

    /**
     * 登录验证
     * @param request
     * @param empId
     * @param model
     * @return
     */
    @RequestMapping("/verifyType")
    public String logIdentify(HttpServletRequest request,@RequestParam("empId") String empId, Model model){
        HttpSession session = request.getSession();
        if(empId.equals("Admin")){
            model.addAttribute("userId",0);
            return "manager/show";
        }else{
            int account = studentService.findUserCount(empId);
            if (account > 0){
                int userId = studentService.findUserByEmpid(empId);
                Student student = studentService.findScoreByEmpid(empId);
                session.setAttribute("userId",userId);
                List<CourseShow> allCourse = courseService.selectAllNoChooseCourse(userId);
                model.addAttribute("allCourse",allCourse);
                model.addAttribute("userId",userId);
                model.addAttribute("currentScore",student.getTotalScore());
                model.addAttribute("status",student.getStatus());
                return "student/info";
            }else{
                return "student/404";
            }

        }

    }




}
