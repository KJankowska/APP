package com.mainapp.generator;

import com.mainapp.teacher.Teacher;
import com.mainapp.tracking.Action;
import com.mainapp.tracking.ActionType;
import com.mainapp.tracking.Event;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataGenerator {

    private DataGenerator() {
    }

    public static Teacher createTeacher() {
        return new Teacher("Zofia", "Nowak", "nowak@mail.com", "665987654");
    }

    public static List<Teacher> generateTeachers() {
        Teacher teacher1 = new Teacher("Zofia", "Nowak", "nowak@mail.com", "665987654");
        teacher1.setId(1L);

        Teacher teacher2 = new Teacher("Adam", "Kowalski", "kowalski@mail.com", "665987111");
        teacher2.setId(2L);

        return Arrays.asList(teacher1, teacher2);
    }

    public static List<Event> generateEvents() {
        Action action = new Action(ActionType.VISIT);
        Event event1 = new Event(action, "ip1", "link1", new Date());
        Event event2 = new Event(action, "ip2", "link2", new Date());
        return Arrays.asList(event1, event2);
    }

}
