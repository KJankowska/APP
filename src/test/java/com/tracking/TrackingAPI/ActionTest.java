package com.tracking.TrackingAPI;

import com.tracking.TrackingAPI.event.EventController;
import com.tracking.TrackingAPI.event.domain.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.validation.Errors;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ActionTest {

    @Mock
    EventRepository eventRepository;

    @Mock
    EventService eventService;

    EventController eventController;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        eventController = new EventController(eventService);
    }

	@Test
	public void postEventEndpointTest() {
		// given
        Event eventToAdd = createEventToAdd();
        Errors noErrors = Mockito.mock(Errors.class);

        // when
        eventController.registerEvent(eventToAdd, noErrors);

        // then
        verify(eventService, times(1)).addEvent(eventToAdd);
    }

    @Test
    public void getEventsEndpointTest()
    {
        // given
        List<Event> events = createExistingEvents();
        given(eventService.findLastEvents(20))
                .willReturn(events);

        // when
        eventController.getLastEvents();

        // then
        verify(eventService, times(1)).findLastEvents(20);
    }

    private Event createEventToAdd() {
        Action actionToAdd = new Action(ActionType.VISIT, 5L);
        return new Event(actionToAdd, "255.255.255.0", "http://mainapp/teacher/5", ZonedDateTime.now());
    }

    private List<Event> createExistingEvents() {
        return Arrays.asList(createEventToAdd(), createEventToAdd(), createEventToAdd());
    }

}
