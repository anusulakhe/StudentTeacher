package com.StudentTeacherAPI.controller;

import com.StudentTeacherAPI.DTO.StudentTeacherDTO;
import com.StudentTeacherAPI.model.Student;
import com.StudentTeacherAPI.service.StudentService;
import com.StudentTeacherAPI.service.StudentTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    StudentTeacherService studentTeacherService;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping("/studentlist")
    public ResponseEntity<List<Student>> getStudents() {
        logger.debug("Starting");
        logger.trace("GetStudent Method called");
        List<Student> students = studentService.getStudent();
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/studentteacherdetails")
    public List<StudentTeacherDTO> getStudentTeacherDetails() {
        return studentTeacherService.getStudentTeacherDetails();
    }

    @PostMapping("/{studentId}/{teacherId}")
    public ResponseEntity<Boolean> mapStudentTeacher(@PathVariable Long studentId, @PathVariable Long teacherId) {
        boolean isMapped = studentTeacherService.MapStudentTeacher(studentId, teacherId);
        return ResponseEntity.ok(isMapped);
    }
//    @PostMapping("/map/{studentId}/{teacherId}")
//    public ResponseEntity<Boolean> updateStudentTeacher(@PathVariable Long studentId, @PathVariable Long teacherId) {
//        boolean isMapped = studentTeacherService.MapStudentTeacher(studentId, teacherId);
//        return ResponseEntity.ok(isMapped);
//    }
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(studentId, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
