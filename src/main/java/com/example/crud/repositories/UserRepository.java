package com.example.crud.repositories;

import com.example.crud.models.Country;
import com.example.crud.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    String FIND_PROJECTS = "select * from users where email = ?1";

    static Specification<User> hasName(String name) {
        if (name == null) {
            return (user, cq, cb) -> cb.isFalse(cb.literal(true));
        }
        return (user, cq, cb) -> cb.equal(user.get("name"), name);
    }

    static Specification<User> hasEmail(String email) {
        if (email == null) {
            return (user, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (user, cq, cb) -> cb.like(user.get("email"), email);
    }

    static Specification<User> createdFrom(LocalDate from) {
        if (from == null) {
            return (user, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (user, cq, cb) -> cb.greaterThanOrEqualTo(user.get("creationDate"), from);
    }

    static Specification<User> hasCountry(Country country) {
        if (country == null) {
            return (user, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (user, cq, cb) -> cb.equal(user.get("country"), country);
    }

    @Query(value = FIND_PROJECTS, nativeQuery = true)
    Optional<User> findByEmail(String email);


}
