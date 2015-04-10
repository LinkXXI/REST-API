package org.rogue.controllers.responses;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kevin on 2015-04-09.
 */
public class Result implements Serializable {

    private String operation;
    private Date date;
    private String status;
    private Object other;

    public Result() {
    }

    public Result(String operation, Date date, String status, Object other) {
        this.operation = operation;
        this.date = date;
        this.status = status;
        this.other = other;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }
}
