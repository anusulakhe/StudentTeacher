package com.StudentTeacherAPI.Repositories;

import com.StudentTeacherAPI.model.Address;
import com.StudentTeacherAPI.model.Student;
import com.StudentTeacherAPI.model.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Transactional
    void deleteByTeacher(Teacher teacher);

    @Transactional
    void deleteByStudent(Student student);

}
