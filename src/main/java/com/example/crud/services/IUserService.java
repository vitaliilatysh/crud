package com.example.crud.services;

import com.example.crud.models.Country;
import com.example.crud.models.User;

import java.util.Date;
import java.util.Optional;

public interface IUserService {

    Iterable<User> findAllUsers();

    Optional<User> findUserById(Long id);

    Optional<User> findUserByName(String name);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByCountry(Country country);

    Iterable<User> findUsersByCreationDateStartedFrom(Date creationDateStartedFrom);

    Iterable<User> findUsersByCreationDateStartedBefore(Date creationDateStartedBefore);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
