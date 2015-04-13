package org.rogue.controllers;

import org.rogue.controllers.Exceptions.BadRequestException;
import org.rogue.controllers.Exceptions.NotAllowedException;
import org.rogue.controllers.Exceptions.NotFoundException;
import org.rogue.controllers.Exceptions.NotImplementedException;
import org.rogue.controllers.responses.Error;
import org.rogue.dao.GenericDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2015-04-10.
 */
public abstract class AbstractBaseController<T extends Serializable> {
    protected GenericDAO<T> dao;

    public abstract Map<String, Object> get() throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract Map<String, Object> post(@RequestBody T t) throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract Map<String, Object> patch() throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract Map<String, Object> put() throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract Map<String, Object> delete() throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract ResponseEntity<Void> options() throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract Map<String, Object> get(@PathVariable int id) throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract Map<String, Object> post(@PathVariable int id) throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract Map<String, Object> patch(@PathVariable int id, @RequestBody T t) throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract Map<String, Object> put(@PathVariable int id, T t) throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract Map<String, Object> delete(@PathVariable int id) throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    public abstract ResponseEntity<Void> options(@PathVariable int id) throws BadRequestException, NotAllowedException, NotFoundException, NotImplementedException;

    /**
     * Exception Handlers
     */

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, Error> handleBadMethod(BadRequestException e) {
        return new HashMap<String, Error>() {{
            put("Error", new Error("Error has occured", e.getMessage(), new Date()));
        }};
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, Error> handleNotFound(NotFoundException e) {
        return new HashMap<String, Error>() {{
            put("Error", new Error("Error has occured", "requested resource not found", new Date()));
        }};
    }

    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public Map<String, Error> handleNotSupported(NotImplementedException e) {
        return new HashMap<String, Error>() {{
            put("Error", new Error("Method not supported", "This method is not implemented yet but may be in the future.", new Date()));
        }};
    }

    @ExceptionHandler(NotAllowedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, Error> handleNotAlowed(NotAllowedException ex) {
        return new HashMap<String, Error>() {{
            put("Error", new Error("Error has occured", "This method is intentionally disallowed.", new Date()));
        }};
    }

    /**
     * HTTP Header set for no cache
     */
    @ModelAttribute
    public void setNoCacheResponseHeader(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
    }
}
