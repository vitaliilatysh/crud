package com.example.crud.controllers;

import com.example.crud.exceptions.ItemAlreadyExistException;
import com.example.crud.exceptions.ItemNotFoundException;
import com.example.crud.models.Country;
import com.example.crud.models.Role;
import com.example.crud.models.User;
import com.example.crud.services.impl.CountryService;
import com.example.crud.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public Iterable<User> showAllUsers() {
        Iterable<User> users = userService.findAllUsers();

        if (!users.iterator().hasNext()) {
            throw new ItemNotFoundException("No users found.");
        }

        return users;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody User user) {
        Optional<Country> countryInDb = countryService.findByName(user.getCountry().getName());
        Optional<User> userInDb = userService.findUserByEmail(user.getEmail());

        if (userInDb.isPresent()) {
            throw new ItemAlreadyExistException("User with such email already exist.");
        }

        if (!countryInDb.isPresent()) {
            throw new IllegalArgumentException(user.getCountry().getName());
        }
        user.setRoles(Role.USER.name());
        user.setCreationDate(Timestamp.from(Instant.now()));
        user.setCountry(countryInDb.get());
        userService.createUser(user);

    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    void delete(@PathVariable("id") Integer userId) {
        Optional<User> user = userService.findUserById(Long.valueOf(userId));

        if (!user.isPresent()) {
            throw new ItemNotFoundException(userId.toString());
        }
        userService.deleteUser(user.get());
    }

    @ResponseBody
    public String filters(@RequestParam String name, @RequestParam String email) {
        return null;
    }
}
