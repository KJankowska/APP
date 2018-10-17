package com.mainapp.teacher;

import com.mainapp.tracking.Action;
import com.mainapp.tracking.ActionType;
import com.mainapp.tracking.Event;
import com.mainapp.tracking.TrackingClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 Kontroler endpointy.
 */
@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private static final Logger logger = Logger.getLogger(TeacherController.class.getSimpleName());

    private TeacherService teacherService;
    private TrackingClient trackingClient;
    private String TEACHER_URI;

    public TeacherController(TeacherService teacherService, TrackingClient trackingClient, @Value("${server.port}") String appPort) {
        this.teacherService = teacherService;
        this.trackingClient = trackingClient;
        this.TEACHER_URI = String.format("http://localhost:%s/teachers/edit?id=", appPort);
    }

    @GetMapping
    public String home() {
        return "redirect:/teachers/all";
    }

    @GetMapping("/all")
    public String getAllTeachers(Model model, HttpServletRequest request) {
        logger.info("About to handle /teachers/all GET request");

        String clientIp = request.getRemoteAddr();
        Event event = new Event(new Action(ActionType.VISIT), clientIp, null, new Date());
        trackingClient.registerEvent(event);

        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);

        return "teachers";
    }

    @GetMapping("/new")
    public String addNewTeacherForm(Model model) {
        logger.info("About to handle /teachers/new GET request");

        model.addAttribute("teacher", new Teacher());
        return "teacher-form";
    }

    @GetMapping("/edit")
    public String editTeacherForm(@RequestParam("id") Long id, Model model) {
        logger.info(String.format("About to handle /teachers/edit?id=%d GET request", id));

        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teacher-form";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute("teacher") @Valid Teacher teacher, BindingResult bindingResult, HttpServletRequest request) {
        logger.info("About to handle /teachers/save POST request");

        if (bindingResult.hasErrors()) {
            return "teacher-form";
        }

        ActionType actionType = teacher.getId() == null ? ActionType.ADD : ActionType.EDIT;
        teacher = teacherService.save(teacher);

        String clientIp = request.getRemoteAddr();
        String link = TEACHER_URI + teacher.getId();

        Action action = new Action(actionType, teacher.getId());
        Event event = new Event(action, clientIp, link, new Date());
        trackingClient.registerEvent(event);

        return "redirect:/teachers/all";
    }

    @GetMapping("/delete")
    public String removeTeacher(@RequestParam("id") Long id, HttpServletRequest request) {
        logger.info(String.format("About to handle /teachers/delete?id=%d request", id));

        String clientIp = request.getRemoteAddr();
        Action action = new Action(ActionType.DELETE, id);
        Event event = new Event(action, clientIp, null, new Date());
        trackingClient.registerEvent(event);

        teacherService.deleteById(id);
        return "redirect:/teachers/all";
    }
}
