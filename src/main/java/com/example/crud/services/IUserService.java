package com.example.crud.services;

import com.example.crud.models.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Iterable<User> findAllUsers();

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    List<User> findAllByCriteria(Specification<User> criteria);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
