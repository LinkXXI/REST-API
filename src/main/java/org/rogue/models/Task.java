package org.rogue.models;

import org.rogue.models.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin on 2015-04-09.
 */
@Entity
public class Task implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;

    @ManyToMany
    private List<User> assignedTo;

    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Task() {
    }

    public Task(String name, String description, List<User> assignedTo, Date dueDate, Status status) {
        this.name = name;
        this.description = description;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<User> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(List<User> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
