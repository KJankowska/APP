package com.mainapp.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfejs repozytorium odpowiedzialnego za operacje na bazie.
 * Implementację dostarcza Spring.
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
