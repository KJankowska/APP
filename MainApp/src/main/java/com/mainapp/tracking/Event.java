package com.mainapp.tracking;

import java.io.Serializable;
import java.util.Date;

/**
 * Reprezentuje event wraz ze wszystkimi wymaganymi danymi.
 */
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Action action;
    private String ip;
    private String link;
    private Date eventTime;

    public Event() {
    }

    public Event(Action action, String ip, String link, Date eventTime) {
        this.action = action;
        this.ip = ip;
        this.link = link;
        this.eventTime = eventTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", action=" + action +
                ", ip='" + ip + '\'' +
                ", link='" + link + '\'' +
                ", eventTime=" + eventTime +
                '}';
    }
}
