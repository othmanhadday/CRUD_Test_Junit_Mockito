package com.springbootcrudexample.serviceimpl;

import com.springbootcrudexample.entity.Student;
import com.springbootcrudexample.exceptions.BadRequestException;
import com.springbootcrudexample.exceptions.MissingAttributeException;
import com.springbootcrudexample.exceptions.StudentNotFoundException;
import com.springbootcrudexample.main.SpringMain;
import com.springbootcrudexample.repository.StudentRepository;
import com.springbootcrudexample.service.StudentService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl underTest;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1l, "test", "test@mail.com", "&&&");
    }

    void checkThrowException(Student student, String msg) {
        AssertionsForClassTypes.assertThatThrownBy(() -> underTest.virifyIfStudentIsExist(student))
                .isInstanceOf(MissingAttributeException.class)
                .hasMessageContaining(msg);
    }

    @Test
    void testMissingAttrStudentObject() {
        //-------------- test name attr -------------------
        //given
        Student student =
                new Student(1l,
                        "",
                        "test@mail.com",
                        "&&&");
        //then
        checkThrowException(student, "Name Field is Empty, Please Fill it");

        //-------------- test email attr -------------------
        //given
        student =
                new Student(1l,
                        "test",
                        "",
                        "&&&");
        //then
        checkThrowException(student, "Email is Empty, Please Fill it");

        //-------------- test rollNumber attr -------------------
        //given
        student =
                new Student(1l,
                        "test",
                        "test@mail.com",
                        "");
        //then
        checkThrowException(student, "Roll Number is Empty, Please Fill it");
    }

    @DisplayName("test for insert new Student method")
    @Test
    void saveNewStudent() {
        //given
        System.out.println("Given-----------");
        System.out.println(student);

        //when
        underTest.save(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        Mockito.verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        System.out.println("capturedStudent--------");
        System.out.println(capturedStudent);

        assertThat(capturedStudent).isEqualTo(student);

    }

    @DisplayName("test Throwing Exception if email Already exist for insertion of new Student method")
    @Test
    void throwExceptionifEmailExistInSaveStudent() {
        //given
        System.out.println("Given-----------");
        System.out.println(student);
        given(studentRepository.existsStudentByEmail(anyString())).willReturn(true);

        //when
        //then
        AssertionsForClassTypes.assertThatThrownBy(() -> underTest.save(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " Already Exist");

        Mockito.verify(studentRepository, never()).save(any());
    }

    @DisplayName("test for update existing Student method")
    @Test
    void testUpdateStudent() {
        //given
        given(studentRepository.existsById(student.getId())).willReturn(true);
        given(studentRepository.save(student)).willReturn(student);

        //when
        Student studentUpdated = underTest.update(student);

        //then
        assertThat(studentUpdated).isEqualTo(student);
        verify(studentRepository).existsById(student.getId());
        verify(studentRepository).save(student);

    }

    @DisplayName("test Throwing Exception if Student doesn't exist for updating a Student method")
    @Test
    void throwExceptionifStudentDoesNotExist() {
        //given
        System.out.println("Given-----------");
        given(studentRepository.existsById(student.getId())).willReturn(false);

        //when
        //then
        AssertionsForClassTypes.assertThatThrownBy(() -> underTest.update(student))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + student.getId() + " does not exists");

        Mockito.verify(studentRepository, never()).save(any(Student.class));
    }


    @DisplayName("test for get Student by id method")
    @Test
    void testGetStudentById() {
        // given
        given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));

        //when
        Student savedStudent = underTest.get(student.getId());
        System.out.println(savedStudent);

        // then
        Mockito.verify(studentRepository).findById(student.getId());
    }

    @DisplayName("test throwing exception if Student doesn't Exist for get Student by id method")
    @Test
    void testGetStudentByIdThrownException() {
        // given
        Long id = 1l;
        given(studentRepository.findById(id))
                .willReturn(Optional.empty());

        //when
        // then
        assertThatThrownBy(() -> underTest.get(id))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + id + " does not exist");

    }

    @DisplayName("test deleting a student")
    @Test
    void testDeleteStudent() {
        //given
        given(studentRepository.existsById(student.getId()))
                .willReturn(true);
        //when
        underTest.delete(student.getId());
        //Then
        verify(studentRepository).deleteById(student.getId());

    }

    @DisplayName("test throwing exception when deleting a student")
    @Test
    void testDeleteStudentIsThrowingException() {
        //given
        given(studentRepository.existsById(student.getId()))
                .willReturn(false);
        //when
        //Then
        assertThatThrownBy(() -> underTest.delete(student.getId()))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + student.getId() + " does not exist");

    }
}