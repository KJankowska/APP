package com.mainapp.unit;

import com.mainapp.teacher.Teacher;
import com.mainapp.teacher.TeacherController;
import com.mainapp.teacher.TeacherService;
import com.mainapp.tracking.TrackingClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

import static com.mainapp.generator.DataGenerator.generateTeachers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class TeacherControllerTest {

    MockMvc mockMvc;

    @Mock
    TeacherService teacherService;

    @Mock
    TrackingClient trackingClient;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new TeacherController(teacherService, trackingClient, "8080"))
                .setViewResolvers(viewResolver).build();
    }

    @Test
    public void getAllTeachersTest() throws Exception {
        // given
        List<Teacher> teachers = generateTeachers();
        given(teacherService.findAll()).willReturn(teachers);

        // when
        mockMvc.perform(get("/teachers/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers"));

        // then
        verify(teacherService, times(1)).findAll();
        verify(trackingClient, times(1)).registerEvent(any());
    }

    @Test
    public void saveEmptyTeacherTest() throws Exception {
        // given
        Teacher teacher = new Teacher(null, null, null, null);

        // when
        // then
        mockMvc.perform(post("/teachers/save")
                        .flashAttr("teacher", teacher))
                .andExpect(view().name("teacher-form"));

        verify(teacherService, times(0)).save(teacher);
        verify(trackingClient, times(0)).registerEvent(any());
    }

    @Test
    public void deleteTeacherTest() throws Exception {
        // given
        Long teacherId = 1L;

        // when
        mockMvc.perform(get("/teachers/delete")
                        .param("id", teacherId.toString()))
                .andExpect(status().is3xxRedirection());

        // then
        verify(trackingClient, times(1)).registerEvent(any());
        verify(teacherService, times(1)).deleteById(teacherId);
    }

}
