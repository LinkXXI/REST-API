package org.rogue.controllers;

import org.rogue.controllers.Exceptions.NotAllowedException;
import org.rogue.controllers.Exceptions.NotFoundException;
import org.rogue.controllers.Exceptions.NotImplementedException;
import org.rogue.controllers.responses.Result;
import org.rogue.dao.GenericDAO;
import org.rogue.models.Contact;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Kevin on 2015-04-10.
 */
@Controller
@RequestMapping("/contacts/")
public class ContactController extends AbstractBaseController<Contact> {

    public ContactController() {
        this.dao = new GenericDAO<>(Contact.class);
    }

    @Override
    @RequestMapping("/")
    @ResponseBody
    public Map<String, Object> get() {
        return new HashMap<String, Object>() {{
            put("Users", dao.getAll().get());
        }};
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> post(@RequestBody Contact contact) {
        if (dao.create(contact)) {
            return new HashMap<String, Object>() {{
                put("result", new Result("create", new Date(), "success", contact.getId()));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("error", new org.rogue.controllers.responses.Error("Unable to create contact", "unable to create contact", new Date()));
            }};
        }
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @ResponseBody
    public Map<String, Object> patch() throws NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> put() throws NotAllowedException {
        throw new NotAllowedException();
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete() throws NotAllowedException {
        throw new NotAllowedException();
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @ResponseBody
    public ResponseEntity<Void> options() throws NotAllowedException {
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.setAllow(new HashSet<>(Arrays.asList(new HttpMethod[]{HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS})));
        return new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
    }

    @Override
    @RequestMapping("/{id}")
    @ResponseBody
    public Map<String, Object> get(@PathVariable int id) throws NotFoundException {
        Optional<Contact> userOptional = dao.getById(id);
        if (userOptional.isPresent()) {
            return new HashMap<String, Object>() {{
                put("Contact", userOptional.get());
            }};
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> post(@PathVariable int id) throws NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public Map<String, Object> patch(@PathVariable int id, @RequestBody Contact contact) {
        if (dao.update(contact)) {
            return new HashMap<String, Object>() {{
                put("success", new Result("update contact", new Date(), "success", contact));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("failure", new Result("update contact", new Date(), "fail", contact));
            }};
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> put(@PathVariable int id, Contact contact) {
        if (dao.update(contact)) {
            return new HashMap<String, Object>() {{
                put("success", new Result("update contact", new Date(), "success", contact));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("failure", new Result("update contact", new Date(), "fail", contact));
            }};
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable int id) {
        if (dao.deleteById(id)) {
            return new HashMap<String, Object>() {{
                put("success", new Result("delete contact", new Date(), "success", id));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("failure", new Result("update contact", new Date(), "fail", id));
            }};
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    @ResponseBody
    public ResponseEntity<Void> options(@PathVariable int id) {
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.setAllow(new HashSet<>(Arrays.asList(new HttpMethod[]{HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS})));
        return new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
    }

}
