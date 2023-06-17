package com.springbootcrudexample.repository;

import java.io.Serializable;

import com.springbootcrudexample.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface StudentRepository
        extends JpaRepository<Student, Serializable> {

    Boolean existsStudentByEmail(String email);

}
