package com.tracking.TrackingAPI.event.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfejs repozytorium odpowiedzialnego za operacje na bazie.
 * Implementację dostarcza Spring.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

}
