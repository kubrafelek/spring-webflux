package com.reactive.springwebflux.controller;

import com.reactive.springwebflux.model.Student;
import com.reactive.springwebflux.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

   private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public Flux<Student> findAll(){
        return studentService.findAll();
    }
}
