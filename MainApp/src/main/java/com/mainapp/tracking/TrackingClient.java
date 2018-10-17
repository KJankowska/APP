package com.mainapp.tracking;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TrackingClient {

    private static final Logger logger = Logger.getLogger(TrackingClient.class.getSimpleName());

    private static final String EVENT = "event";
    private static final String LOGS = "logs";
    private String TRACKING_HOST;
    private Client httpClient;

    public TrackingClient(Client httpClient, @Value("${tracking.port}") String trackingHost) {
        this.httpClient = httpClient;
        this.TRACKING_HOST = String.format("http://localhost:%s/tracking", trackingHost);
    }

    public boolean registerEvent(Event event) {
        Invocation.Builder eventRequestBuilder = httpClient
                .target(TRACKING_HOST)
                .path(EVENT).request(MediaType.APPLICATION_JSON_TYPE);

        try {
            eventRequestBuilder.post(Entity.entity(event, MediaType.APPLICATION_JSON_TYPE));
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Could not register event. Verify whether trackingAPI is running. Exception: {0}, Message: {1}, Cause: {2}",
                    new Object[]{e.getMessage(), e.getClass(), e.getCause()});
        }

        logger.info("Registered event: " + event);
        return true;
    }

    public List<Event> getEventLogs() {
        Invocation.Builder eventRequestBuilder = httpClient
                .target(TRACKING_HOST)
                .path(LOGS).request(MediaType.APPLICATION_JSON_TYPE);

        List<Event> events = Collections.emptyList();

        try {
            events = eventRequestBuilder.get(new GenericType<List<Event>>() {
            });
            logger.info("Events logs successfully requested");
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Could not fetch event logs. Verify whether trackingAPI is running. Exception: {0}, Message: {1}, Cause: {2}",
                    new Object[]{e.getMessage(), e.getClass(), e.getCause()});
        }

        return events;
    }
}
