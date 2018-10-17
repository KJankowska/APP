package com.mainapp.integration;

import com.mainapp.MainappApplication;
import com.mainapp.teacher.Teacher;
import com.mainapp.teacher.TeacherController;
import com.mainapp.teacher.TeacherRepository;
import com.mainapp.tracking.ActionType;
import com.mainapp.tracking.Event;
import com.mainapp.tracking.TrackingClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MainappApplication.class })
@TestPropertySource(locations="classpath:application.properties")
@WebAppConfiguration
public class IntegrationTestIT {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TeacherController teacherController;

    @Autowired
    TrackingClient trackingClient;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	@Test
	public void flowWorks() throws Exception {
	    // given
        Teacher teacher = createTeacher();

        // when add teacher
        // then assert request successful
        mockMvc.perform(post("/teachers/save")
                        .flashAttr("teacher", teacher))
                .andExpect(status().is3xxRedirection());

        // then assert teacher added to db
        List<Teacher> teachers = teacherRepository.findAll();
        assertEquals("Should save teacher to db", 1, teachers.size());

        Teacher savedTeacher = teachers.get(0);
        assertEquals("First name should match", teacher.getFirstName(), savedTeacher.getFirstName());
        assertEquals("Last name should match", teacher.getLastName(), savedTeacher.getLastName());

        // then assert event added
        Event event = trackingClient.getEventLogs().stream()
                .filter(log -> log.getAction().getSubjectId() == savedTeacher.getId())
                .findFirst()
                .orElseThrow(() -> new AssertionError("Event not found. Verify whether tracking api is UP"));
        assertEquals("Should be ADD event", ActionType.ADD, event.getAction().getType());


        // when delete teacher
        // then assert request successful
        mockMvc.perform(get("/teachers/delete")
                        .param("id", teacher.getId().toString()))
                .andExpect(status().is3xxRedirection());

        // then assert teacher removed from db
        boolean teacherDeleted = !teacherRepository.existsById(teacher.getId());
        assertTrue("Teacher should be removed", teacherDeleted);
    }

    private Teacher createTeacher() {
	    return new Teacher("Zofia", "Nowak", "nowakz@mail.com", "660123456");
    }


}
