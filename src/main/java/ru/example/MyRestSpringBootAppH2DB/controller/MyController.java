package ru.example.MyRestSpringBootAppH2DB.controller;

import ru.example.MyRestSpringBootAppH2DB.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.MyRestSpringBootAppH2DB.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MyController {
    @Autowired
    private StudentService studentService;


    @GetMapping("/students")
    public ResponseEntity<List<Student>> showAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        if (!allStudents.isEmpty()) {
            return new ResponseEntity<>(allStudents, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/students")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        if (savedStudent != null) {
            return new ResponseEntity<>("СТУДЕНТ ДОБАВЛЕН", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Не удалось добавить студента", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/students")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.saveStudent(student);
        if (updatedStudent != null) {
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return new ResponseEntity<>("СТУДЕНТ УДАЛЕН", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Студент не найден", HttpStatus.NOT_FOUND);
        }
    }


}
