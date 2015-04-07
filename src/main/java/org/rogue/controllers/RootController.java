package org.rogue.controllers;

import org.rogue.controllers.Exceptions.BadRequestException;
import org.rogue.controllers.Exceptions.NotAllowedException;
import org.rogue.controllers.Exceptions.NotFoundException;
import org.rogue.controllers.Exceptions.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Kevin on 2015-04-07.
 */
@Controller
public class RootController {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleBadMethod(BadRequestException e) {
        return "woops";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFound(NotFoundException e) {
        return "not there";
    }

    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public String handleNotSupported(NotImplementedException e) {
        return "Not implemented";
    }

    @ExceptionHandler(NotAllowedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public String handleNotAlowed(NotAllowedException ex) {
        return "Not Allowed";
    }
}
