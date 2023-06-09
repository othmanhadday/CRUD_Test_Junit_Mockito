package com.springbootcrudexample.repository;


import com.springbootcrudexample.entity.Student;
import com.springbootcrudexample.main.SpringMain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = SpringMain.class)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldCheckIfStudentExistsEmail() {
        //given
        String email = "test@gmail.com";
        Student student = new Student("nanan", email, "dddd");
        Student student1 = underTest.save(student);
        System.out.println(student1.toString());

        //when
        boolean emailExist = underTest.existsStudentByEmail(email);
        System.out.println(emailExist);

        //then
        assertThat(emailExist).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailNotExists() {
        //given
        String email = "test@gmail.com";
        //Student student = new Student("nanan", email, "dddd");
        //Student student1 = underTest.save(student);
        //System.out.println(student1.toString());

        //when
        boolean emailExist = underTest.existsStudentByEmail(email);
        System.out.println(emailExist);

        //then
        assertThat(emailExist).isFalse();
    }

}