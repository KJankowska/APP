package com.mainapp.tracking;

import java.io.Serializable;

/**
 * Reprezentuje typ akcji i id nauczyciela, na którym wykonano akcję.
 */
public class Action implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private ActionType type;
    private Long subjectId;

    public Action() {
    }

    public Action(ActionType type) {
        this.type = type;
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
