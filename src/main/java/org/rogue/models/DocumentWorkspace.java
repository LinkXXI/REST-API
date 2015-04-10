package org.rogue.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Kevin on 2015-04-09.
 */

@Entity
public class DocumentWorkspace implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String googleDriveUriPath;

    public DocumentWorkspace() {
    }

    public DocumentWorkspace(String googleDriveUriPath) {
        this.googleDriveUriPath = googleDriveUriPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoogleDriveUriPath() {
        return googleDriveUriPath;
    }

    public void setGoogleDriveUriPath(String googleDriveUriPath) {
        this.googleDriveUriPath = googleDriveUriPath;
    }
}
