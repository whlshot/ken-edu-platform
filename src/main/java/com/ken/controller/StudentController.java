package com.ken.controller;

import com.ken.entity.Student;
import com.ken.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yhq
 * @date 2019/1/14
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/getAll")
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/getOne")
    public Student getOneStudent() {
        return studentService.getOneStudent();
    }
}
