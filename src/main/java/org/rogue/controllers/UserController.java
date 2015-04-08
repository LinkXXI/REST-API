package org.rogue.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.rogue.controllers.Exceptions.BadRequestException;
import org.rogue.controllers.Exceptions.NotAllowedException;
import org.rogue.controllers.Exceptions.NotFoundException;
import org.rogue.controllers.Exceptions.NotImplementedException;
import org.rogue.dao.GenericDAO;
import org.rogue.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kevin on 2015-04-07.
 */
@Controller
@RequestMapping("/users/")
public class UserController {

    GenericDAO<User> userDAO = new GenericDAO<>(User.class);

    /**
     * Users Root Handlers
     */

    @RequestMapping("/")
    public
    @ResponseBody
    List<User> getList() {
        return userDAO.getAll().get();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean postList(@RequestBody User user, HttpServletResponse response) {
        response.setStatus(HttpStatus.CREATED.value());
        return userDAO.create(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public void patchList() throws NotImplementedException {
        throw new NotImplementedException();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void putList() throws NotAllowedException {
        throw new NotAllowedException();
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void deleteList() throws NotAllowedException {
        throw new NotAllowedException();
    }

    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    public void optionsList(HttpServletResponse response) {
        response.setHeader("Allow", "GET, POST");
    }

    @RequestMapping(value = "/", method = RequestMethod.TRACE)
    public void traceList(HttpServletResponse response) {
        response.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
    }

    /**
     * User ID handlers
     */

    @RequestMapping("{id}")
    public
    @ResponseBody
    User getId(@PathVariable int id) {
        return userDAO.getById(id).get();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public void postId(@PathVariable int id) throws NotImplementedException {
        throw new NotImplementedException();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public
    @ResponseBody
    boolean patchId(@PathVariable int id, @RequestBody User user) {
        return userDAO.update(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public boolean putId(@PathVariable int id, @RequestBody User user) {
        return userDAO.update(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public boolean deleteId(@PathVariable int id) {
        return userDAO.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.OPTIONS)
    public void optionsId(@PathVariable int id, HttpServletResponse response) {
        response.setHeader("Allow", "GET, POST");
    }

    /**
     * Exception Handlers
     */

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void handleBadMethod(BadRequestException e) {
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleNotFound(NotFoundException e) {
    }

    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public void handleNotSupported(NotImplementedException e) {
    }

    @ExceptionHandler(NotAllowedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public void handleNotAlowed(NotAllowedException ex) {
    }
}
