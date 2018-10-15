package com.tracking.TrackingAPI.event.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Reprezentuje typ akcji i id trenera, na którym wykonano akcję.
 * Id nadawane jest automatycznie przy zapisie do bazy.
 */
@Entity
@Table(name = "action")
public class Action implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ActionType type;

    @NotNull
    @Column(name = "subject_id")
    private Long subjectId;

    public Action() {
    }

    public Action(ActionType type, Long subjectId) {
        this.type = type;
        this.subjectId = subjectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", type=" + type +
                ", subjectId=" + subjectId +
                '}';
    }
}
