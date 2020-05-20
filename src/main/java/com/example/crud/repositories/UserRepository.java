package com.example.crud.repositories;

import com.example.crud.models.Country;
import com.example.crud.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByCountry(Country country);

}
