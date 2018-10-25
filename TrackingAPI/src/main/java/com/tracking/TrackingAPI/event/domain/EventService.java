package com.tracking.TrackingAPI.event.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findFirstEvents(int number) {
        return eventRepository.findAll(
                PageRequest.of(0, number,
                        Sort.by("eventTime").ascending()))
                .getContent();
    }
}
