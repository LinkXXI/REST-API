package org.rogue.controllers.responses;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kevin on 2015-04-09.
 */
public class Error implements Serializable {
    private String devMessage;
    private String message;
    private Date date;

    public Error() {
    }

    public Error(String devMessage, String message, Date date) {
        this.devMessage = devMessage;
        this.message = message;
        this.date = date;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
