package org.rogue.controllers;

import org.rogue.controllers.Exceptions.NotAllowedException;
import org.rogue.controllers.Exceptions.NotFoundException;
import org.rogue.controllers.Exceptions.NotImplementedException;
import org.rogue.controllers.responses.Result;
import org.rogue.dao.GenericDAO;
import org.rogue.models.Address;
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
@RequestMapping("/addresses")
public class AddressController extends AbstractBaseController<Address> {

    public AddressController() {
        this.dao = new GenericDAO<>(Address.class);
    }

    @Override
    @RequestMapping("/")
    @ResponseBody
    Map<String, Object> get() {
        return new HashMap<String, Object>() {{
            put("Users", dao.getAll().get());
        }};
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> post(@RequestBody Address address) {
        if (dao.create(address)) {
            return new HashMap<String, Object>() {{
                put("result", new Result("create", new Date(), "success", address.getId()));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("error", new org.rogue.controllers.responses.Error("Unable to create address", "unable to create address", new Date()));
            }};
        }
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @ResponseBody
    Map<String, Object> patch() throws NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    Map<String, Object> put() throws NotAllowedException {
        throw new NotAllowedException();
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    Map<String, Object> delete() throws NotAllowedException {
        throw new NotAllowedException();
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @ResponseBody
    ResponseEntity<Void> options() throws NotAllowedException {
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.setAllow(new HashSet<>(Arrays.asList(new HttpMethod[]{HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS})));
        return new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
    }

    @Override
    @RequestMapping("/{id}")
    @ResponseBody
    Map<String, Object> get(@PathVariable int id) throws NotFoundException {
        Optional<Address> userOptional = dao.getById(id);
        if (userOptional.isPresent()) {
            return new HashMap<String, Object>() {{
                put("Address", userOptional.get());
            }};
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> post(@PathVariable int id) throws NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    Map<String, Object> patch(@PathVariable int id, @RequestBody Address user) {
        if (dao.update(user)) {
            return new HashMap<String, Object>() {{
                put("success", new Result("update address", new Date(), "success", user));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("failure", new Result("update address", new Date(), "fail", user));
            }};
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    Map<String, Object> put(@PathVariable int id, Address user) {
        if (dao.update(user)) {
            return new HashMap<String, Object>() {{
                put("success", new Result("update address", new Date(), "success", user));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("failure", new Result("update address", new Date(), "fail", user));
            }};
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    Map<String, Object> delete(@PathVariable int id) {
        if (dao.deleteById(id)) {
            return new HashMap<String, Object>() {{
                put("success", new Result("delete address", new Date(), "success", id));
            }};
        } else {
            return new HashMap<String, Object>() {{
                put("failure", new Result("update address", new Date(), "fail", id));
            }};
        }
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
    @ResponseBody
    ResponseEntity<Void> options(@PathVariable int id) {
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.setAllow(new HashSet<>(Arrays.asList(new HttpMethod[]{HttpMethod.GET, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS})));
        return new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
    }
}
