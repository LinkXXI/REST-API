package org.rogue.controllers;

import org.rogue.controllers.Exceptions.BadRequestException;
import org.rogue.controllers.Exceptions.NotAllowedException;
import org.rogue.controllers.Exceptions.NotFoundException;
import org.rogue.controllers.Exceptions.NotImplementedException;
import org.rogue.controllers.responses.Error;
import org.rogue.controllers.responses.Result;
import org.rogue.dao.GenericDAO;
import org.rogue.models.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Kevin on 2015-04-07.
 * <p>
 * NO LONGER USED, replaced with abstract base & new controller
 */
//@Controller
//@RequestMapping("/users/")
@Deprecated
public class UserControllerDeprecated {

    GenericDAO<User> userDAO = new GenericDAO<>(User.class);

    /**
     * Users Root Handlers
     */

    @RequestMapping("/")
    @ResponseBody
    public HashMap<String, Object> getList() {
        return new HashMap<String, Object>() {{
            put("Users", userDAO.getAll().get());
        }};
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<String, Object> postList(@RequestBody User user) {
        if (userDAO.create(user)) {
            return new HashMap<String, Object>() {{
                put("result", new Result("create", new Date(), "success", user.getId()));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("error", new Error("Unable to create user", "unable to create user", new Date()));
            }};
        }
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> optionsList() {
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.setAllow(new HashSet<>(Arrays.asList(new HttpMethod[]{HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS})));
        return new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
    }

    /**
     * User ID handlers
     */

    @RequestMapping("{id}")
    @ResponseBody
    public Map<String, Object> getId(@PathVariable int id) throws NotFoundException {
        Optional<User> userOptional = userDAO.getById(id);
        if (userOptional.isPresent()) {
            return new HashMap<String, Object>() {{
                put("User", userOptional.get());
            }};
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public void postId(@PathVariable int id) throws NotImplementedException {
        throw new NotImplementedException();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public Map<String, Object> patchId(@PathVariable int id, @RequestBody User user) {
        if (userDAO.update(user)) {
            return new HashMap<String, Object>() {{
                put("success", new Result("update user", new Date(), "success", user));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("failure", new Result("update user", new Date(), "fail", user));
            }};
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Map<String, Object> putId(@PathVariable int id, @RequestBody User user) {
        if (userDAO.update(user)) {
            return new HashMap<String, Object>() {{
                put("success", new Result("update user", new Date(), "success", user));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("failure", new Result("update user", new Date(), "fail", user));
            }};
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteId(@PathVariable int id) {
        if (userDAO.deleteById(id)) {
            return new HashMap<String, Object>() {{
                put("success", new Result("delete user", new Date(), "success", id));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("failure", new Result("update user", new Date(), "fail", id));
            }};
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.OPTIONS)
    public void optionsId(@PathVariable int id, HttpServletResponse response) {
        response.setHeader("Allow", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }

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
