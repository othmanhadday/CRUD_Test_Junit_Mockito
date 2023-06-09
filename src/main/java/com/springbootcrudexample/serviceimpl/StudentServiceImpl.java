package com.springbootcrudexample.serviceimpl;

import java.util.*;

import com.springbootcrudexample.exceptions.BadRequestException;
import com.springbootcrudexample.exceptions.MissingAttributeException;
import com.springbootcrudexample.exceptions.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootcrudexample.entity.Student;
import com.springbootcrudexample.repository.StudentRepository;
import com.springbootcrudexample.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private String notFoundExceptionMsg;

    public void setNotFoundExceptionMsg(Long id) {
        this.notFoundExceptionMsg = "Student with id " + id + " does not exists";
    }

    public String getNotFoundExceptionMsg() {
        return notFoundExceptionMsg;
    }

    @Transactional
    public Student save(Student student) {
        virifyIfStudentIsExist(student);
        if (studentRepository.existsStudentByEmail(student.getEmail())) {
            throw new BadRequestException("Email " + student.getEmail() + " Already Exist");
        }
        Student createResponse = studentRepository.save(student);
        return createResponse;
    }

    public void virifyIfStudentIsExist(Student student) {
        if (student.getName().isEmpty() || student.getName().isBlank()) {
            throw new MissingAttributeException("Name Field is Empty, Please Fill it");
        }
        if (student.getEmail().isEmpty() || student.getEmail().isBlank()) {
            throw new MissingAttributeException("Email is Empty, Please Fill it");
        }
        if (student.getRollNumber().isEmpty() || student.getRollNumber().isBlank()) {
            throw new MissingAttributeException("Roll Number is Empty, Please Fill it");
        }
    }

    @Transactional
    public Student update(Student student) {
        virifyIfStudentIsExist(student);
        if (!studentRepository.existsById(student.getId())) {
            setNotFoundExceptionMsg(student.getId());
            throw new StudentNotFoundException(getNotFoundExceptionMsg());
        }
        Student updateResponse = studentRepository.save(student);
        return updateResponse;
    }

    @Transactional
    public Student get(Long id) {
        Student response = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " does not exists"));
        return response;
    }

    @Override
    public List<Student> get() {
        return studentRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            setNotFoundExceptionMsg(id);
            System.out.println(getNotFoundExceptionMsg());
            throw new StudentNotFoundException(getNotFoundExceptionMsg());
        }
        studentRepository.deleteById(id);
    }
}
