package com.oldsix.test.controller;

import com.oldsix.test.service.CourseService;
import com.oldsix.test.service.ManagerService;
import com.oldsix.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员控制器
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

}
