package com.springbootcrudexample.service;

import org.springframework.stereotype.Component;

import com.springbootcrudexample.entity.Student;

import java.util.List;
import java.util.Optional;

@Component
public interface StudentService {

    public Student save(Student student);

    public Student update(Student student);

    public Student get(Long id);

    public List<Student> get();

    public void delete(Long id);
}
