package org.rogue.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kevin on 2015-04-09.
 */
@Entity
public class Report implements Serializable {

    @GeneratedValue
    @Id
    private int id;

    @Column(unique = true)
    private String title;
    private String description;
    private Date dateCreated;
    private String body;

    public Report() {
    }

    public Report(String title, String description, Date dateCreated, String body) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
