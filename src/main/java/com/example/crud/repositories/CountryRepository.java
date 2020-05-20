package com.example.crud.repositories;

import com.example.crud.models.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Long> {

    Optional<Country> findByName(String countryName);
}
