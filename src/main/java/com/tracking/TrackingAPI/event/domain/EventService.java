package com.tracking.TrackingAPI.event.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Klasa serwisu, to ona wywołuje bezprośrednio metody repozytorium.
 */
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

    public List<Event> findLastEvents(int number)
    {
        return eventRepository.findAll(PageRequest.of(0, number))
                .getContent();
    }
}
