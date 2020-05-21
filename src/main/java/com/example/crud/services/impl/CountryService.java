package com.example.crud.services.impl;

import com.example.crud.models.Country;
import com.example.crud.repositories.CountryRepository;
import com.example.crud.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService implements ICountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }

}
