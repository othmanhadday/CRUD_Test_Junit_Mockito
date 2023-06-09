package com.springbootcrudexample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootcrudexample.entity.Student;
import com.springbootcrudexample.main.SpringMain;
import com.springbootcrudexample.serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
class StudentControllerTest {

    @Mock
    private StudentServiceImpl studentService;

    @InjectMocks
    private StudentController studentController;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new Student(1l,
                "test",
                "test@mail.com",
                "&&&");
    }

    @Test
    void createStudent(){
        when(studentService.save(student)).thenReturn(student);

        ResponseEntity<Student> response = studentController.createStudent(student);
        System.out.println("response.getBody()");
        System.out.println(response.getBody());

        // Verify the response
        assertEquals(student, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void update(){
        when(studentService.update(student)).thenReturn(student);

        ResponseEntity<Student> response = studentController.updateStudent(student);
        System.out.println("response.getBody()");
        System.out.println(response.getBody());

        // Verify the response
        assertEquals(student, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getStudent() throws Exception {
        // Mock the behavior of the studentService
        when(studentService.get(student.getId())).thenReturn(student);

        // Call the getStudent() method of the studentController
        ResponseEntity<Student> response = studentController.getStudent(student.getId());

        System.out.println("response.getBody()");
        System.out.println(response.getBody());
        // Verify the response
        assertEquals(student, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void deleteStudent() {
        // Mock the behavior of the studentService

        // Call the getStudent() method of the studentController
        ResponseEntity<String> response = studentController.deleteStudent(student.getId());

        System.out.println("response.getBody()");
        System.out.println(response);
        // Verify the response
        verify(studentService).delete(student.getId());
        assertEquals(204, response.getStatusCodeValue());
        assertEquals(null, response.getBody());
    }

}