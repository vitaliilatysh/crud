package com.example.crud.repositories;

import com.example.crud.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    static Specification<User> hasName(String name) {
        return (user, cq, cb) -> cb.equal(user.get("name"), name);
    }

    static Specification<User> hasEmail(String email) {
        return (user, cq, cb) -> cb.like(user.get("email"), email);
    }
}
