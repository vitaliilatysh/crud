package com.example.crud.services.impl;

import com.example.crud.models.Country;
import com.example.crud.models.User;
import com.example.crud.repositories.UserRepository;
import com.example.crud.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByCountry(Country country) {
        return userRepository.findByCountry(country);
    }

    @Override
    public Iterable<User> findUsersByCreationDateStartedFrom(Date creationDateStartedFrom) {
        return null;
    }

    @Override
    public Iterable<User> findUsersByCreationDateStartedBefore(Date creationDateStartedBefore) {
        return null;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
