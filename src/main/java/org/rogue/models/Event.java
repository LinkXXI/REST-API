package org.rogue.models;

import org.rogue.models.enums.Status;
import org.rogue.models.enums.Theme;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin on 2015-04-09.
 */
@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User owner;

    @Column(unique = true)
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Theme theme;
    @Enumerated(EnumType.STRING)
    private Status status;

    private Date dateTime;

    @OneToMany
    private List<Task> tasks;

    @ManyToOne
    private Venue venue;

    public Event() {
    }

    public Event(User owner, String name, String description, Theme theme, Status status, Date dateTime, List<Task> tasks, Venue venue) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.theme = theme;
        this.status = status;
        this.dateTime = dateTime;
        this.tasks = tasks;
        this.venue = venue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
