package com.mainapp.unit;

import com.mainapp.teacher.Teacher;
import com.mainapp.teacher.TeacherRepository;
import com.mainapp.teacher.TeacherService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;
import java.util.Optional;

import static com.mainapp.generator.DataGenerator.createTeacher;
import static com.mainapp.generator.DataGenerator.generateTeachers;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TeacherServiceTest {

    @Mock
    TeacherRepository teacherRepository;

    TeacherService teacherService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        teacherService = new TeacherService(teacherRepository);
    }

    @Test
    public void findByIdTest() {
        // given
        Long id = 1L;
        Teacher expectedTeacher = createTeacher();
        expectedTeacher.setId(id);
        given(teacherRepository.findById(id))
                .willReturn(Optional.ofNullable(expectedTeacher));

        // when
        Teacher actualTeacher = teacherService.findById(id);

        // then
        assertEquals("Should return teacher", expectedTeacher, actualTeacher);
        verify(teacherRepository, times(1)).findById(id);
    }

    @Test
    public void findAll() {
        // given
        List<Teacher> savedTeachers = generateTeachers();
        given(teacherRepository.findAll())
                .willReturn(savedTeachers);

        // when
        List<Teacher> actualTeachers = teacherService.findAll();

        // then
        assertEquals(2, actualTeachers.size());
        verify(teacherRepository, times(1)).findAll();
    }

    @Test
    public void save() {
        // given
        Teacher teacher = createTeacher();

        // when
        teacherService.save(teacher);

        // then
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    public void deleteById() {
        // given
        Long id = 1L;

        // when
        teacherService.deleteById(id);

        // then
        verify(teacherRepository, times(1)).deleteById(id);
    }

}