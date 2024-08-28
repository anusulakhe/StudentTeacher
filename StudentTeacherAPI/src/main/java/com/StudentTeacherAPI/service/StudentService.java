package com.StudentTeacherAPI.service;

import com.StudentTeacherAPI.Repositories.*;
import com.StudentTeacherAPI.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;
    AddressRepository addressRepository;

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        if (student.getAddress() != null) {
        for (Address address : student.getAddress()) {
            address.setStudent(student); // Ensure the bidirectional relationship is set
        }
        }

        return studentRepository.save(student);
    }


        @Transactional
    public Student updateStudent(Long studentId, Student updateStudent) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));

            if (updateStudent.getAddress() != null) {
                existingStudent.getAddress().clear();
                existingStudent.getAddress().addAll(updateStudent.getAddress());
                existingStudent.getAddress().forEach(address -> address.setStudent(existingStudent));
            }

        existingStudent.setFirstName(updateStudent.getFirstName());
        existingStudent.setLastName(updateStudent.getLastName());
        existingStudent.setGender(updateStudent.getGender());
        existingStudent.setStandard(updateStudent.getStandard());
        existingStudent.setEmail(updateStudent.getEmail());

        return studentRepository.save(existingStudent);
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        System.out.println("Attempting to delete student with ID: " + studentId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Delete Student :" + studentId));

        studentRepository.delete(student);
    }

}