package com.example.crud.controllers;

import com.example.crud.models.Country;
import com.example.crud.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @Autowired
    private CountryRepository countryService;

    @GetMapping("/countries")
    public Iterable<Country> showAllUsers() {
        return countryService.findAll();
    }
}
