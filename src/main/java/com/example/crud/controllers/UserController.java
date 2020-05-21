package com.example.crud.controllers;

import com.example.crud.exceptions.ItemAlreadyExistException;
import com.example.crud.exceptions.ItemNotFoundException;
import com.example.crud.models.Country;
import com.example.crud.models.Role;
import com.example.crud.models.User;
import com.example.crud.repositories.CountryRepository;
import com.example.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.crud.repositories.UserRepository.*;
import static org.springframework.data.jpa.domain.Specification.where;

@RestController
public class UserController {

    @Autowired
    private UserRepository userService;

    @Autowired
    private CountryRepository countryService;

    @GetMapping("/users")
    public Iterable<User> showAllUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody User user) {
        Country countryRequested = user.getCountry();
        Optional<Country> countryInDb = countryService.findById(countryRequested.getId());
        Optional<User> userInDb = userService.findByEmail(user.getEmail());

        if (userInDb.isPresent()) {
            throw new ItemAlreadyExistException("User with such email already exist.");
        }

        if (!countryInDb.isPresent()) {
            throw new IllegalArgumentException(user.getCountry().getName());
        }
        user.setRoles(Role.USER.name());
        user.setCreationDate(LocalDateTime.now());
        user.setCountry(countryInDb.get());
        userService.save(user);

    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") Integer userId) {
        Optional<User> user = userService.findById(Long.valueOf(userId));

        if (!user.isPresent()) {
            throw new ItemNotFoundException(userId.toString());
        }
        userService.delete(user.get());
    }

    @GetMapping("/users/filter")
    public Iterable<User> filters(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String email,
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate from,
                                  @RequestParam(required = false) String country) {
        Country byShortCode = null;
        if (country != null) {
            byShortCode = countryService.findByCountryCode(country).orElse(null);
        }

        return userService.findAll(where(hasName(name))
                .and(hasEmail(email))
                .and(hasCountry(byShortCode))
                .and(createdFrom(from)));
    }

    @PutMapping("/users/{id}")
    public void update(@RequestBody User user, @PathVariable("id") Integer userId) {
        Optional<User> userInDb = userService.findById(Long.valueOf(userId));
        if (!userInDb.isPresent()) {
            throw new ItemNotFoundException(userId.toString());
        }

        Country countryToSet = user.getCountry();
        Optional<Country> countryInDb = countryService.findById(countryToSet.getId());
        if (!countryInDb.isPresent()) {
            throw new ItemNotFoundException("No country found by id: " + countryToSet.getId());
        }

        User userToUpdate = userInDb.get();
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setCountry(countryInDb.get());

        userService.save(userInDb.get());
    }
}
