package com.example.crud.repositories;

import com.example.crud.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    String FIND_PROJECTS = "select * from users where email = ?1";

    @Query(value = FIND_PROJECTS, nativeQuery = true)
    Optional<User> findByEmail(String email);

    static Specification<User> hasName(String name) {
        return (user, cq, cb) -> cb.equal(user.get("name"), name);
    }

    static Specification<User> hasEmail(String email) {
        return (user, cq, cb) -> cb.like(user.get("email"), email);
    }
}
