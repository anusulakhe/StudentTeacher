package com.StudentTeacherAPI.controller;

import com.StudentTeacherAPI.model.Student;
import com.StudentTeacherAPI.model.Teacher;
import com.StudentTeacherAPI.service.TeacherService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/techerlist")
    public ResponseEntity<List<Teacher>>getTeachers() {
        logger.debug("Starting");
        logger.trace("GetStudent Method called");
        List<Teacher> teachers = teacherService.getTeachers();
        return ResponseEntity.ok(teachers);
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Teacher> addteacher(@RequestBody Teacher teacher) {
        try {
            Teacher saveTeacher = teacherService.saveteacher(teacher);
            return new ResponseEntity<>(saveTeacher, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @PutMapping("/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher teacher) {

        Teacher updateTeacher = teacherService.updateTeacher(teacherId, teacher);
        return ResponseEntity.ok(updateTeacher);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.noContent().build();
    }

}
