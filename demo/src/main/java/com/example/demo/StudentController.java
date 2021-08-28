package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.Scanner;

@RestController
//@RequestMapping(path = "/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "search")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping(path = "search")
    public void ZarejestrujNowegoStudenta(@RequestBody Student student){
        studentService.dodajNowegoStudenta(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void UsunStudenta(@PathVariable("studentId") Integer studentId) {
        studentService.usunStudenta(studentId);
    }

    @PutMapping(path = "{id}")
    public void zaktualizujStudenta(@PathVariable("id") Integer id,@RequestParam(required = false) String imie,
                                    @RequestParam(required = false) String nazwisko,
                                    @RequestParam(required = false) String email){
        studentService.aktualizajaStudenta(id,imie,nazwisko,email);
    }
    @GetMapping(path="test")
    public String hello(){
        return "Hello";
    }

}
