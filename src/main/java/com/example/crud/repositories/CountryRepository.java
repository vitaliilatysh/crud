package com.example.crud.repositories;

import com.example.crud.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    String FIND_COUNTRY_BY_CODE = "select * from country c where c.short_code = ?1";

    @Query(value = FIND_COUNTRY_BY_CODE, nativeQuery = true)
    Optional<Country> findByCountryCode(String code);
}
