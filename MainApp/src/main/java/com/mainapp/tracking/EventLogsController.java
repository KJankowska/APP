package com.mainapp.tracking;

import com.mainapp.teacher.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/logs")
public class EventLogsController {

    private static final Logger logger = Logger.getLogger(EventLogsController.class.getSimpleName());

    private TrackingClient trackingClient;
    private TeacherService teacherService;

    public EventLogsController(TrackingClient trackingClient, TeacherService teacherService) {
        this.trackingClient = trackingClient;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String findFirstEvents(Model model) {
        logger.info("About to handle /logs GET request");

        List<Event> eventLogs = trackingClient.getEventLogs().stream()
                .peek(event -> {
                    if (event.getAction().getSubjectId() != null && !teacherService.exists(event.getAction().getSubjectId()))
                        event.setLink(null);
                }).collect(toList());

        model.addAttribute("logs", eventLogs);
        return "logs";
    }
//    /**
//     * nowa metoda, ktora uderza do nowego endpointu na wszstyskie logi
//     */
//    @GetMapping
//    public String findAllEvents(Model model) {
//        logger.info("About to handle /logs GET request");
//
//        List<Event> eventLogs = trackingClient.getEventLogs().stream()
//                .peek(event -> {
//                    if (event.getAction().getSubjectId() != null && !teacherService.exists(event.getAction().getSubjectId()))
//                        event.setLink(null);
//                }).collect(toList());
//
//        model.addAttribute("logs", eventLogs);
//        return "logs";
//    }

}
