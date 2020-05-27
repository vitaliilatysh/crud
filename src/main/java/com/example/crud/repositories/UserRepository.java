package com.example.crud.repositories;

import com.example.crud.models.Country;
import com.example.crud.models.Role;
import com.example.crud.models.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    String FIND_PROJECTS = "select * from users where email = ?1";

    static Specification<User> hasName(String name) {
        if (name == null) {
            return (user, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (user, cq, cb) -> cb.equal(user.get("name"), name);
    }

    static Specification<User> hasEmail(String email) {
        if (email == null) {
            return (user, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (user, cq, cb) -> cb.like(user.get("email"), email);
    }

    static Specification<User> hasCountry(Country country) {
        if (country == null) {
            return (user, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (user, cq, cb) -> cb.equal(user.get("country"), country);
    }

    static Specification<User> createdFrom(final LocalDate localDate) {
        if (localDate == null) {
            return (user, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0, 0));

        return (user, cq, cb) -> cb.greaterThanOrEqualTo(user.get("creationDate"), localDateTime);
    }

    static Specification<User> createdTo(final LocalDate localDate) {
        if (localDate == null) {
            return (user, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0, 0));

        return (user, cq, cb) -> cb.lessThan(user.get("creationDate"), localDateTime);
    }

    static Specification<User> hasRoleName(final String roleName) {

        return (root, query, cb) -> {
            query.distinct(true);
            Subquery<Role> roleSubQuery = query.subquery(Role.class);
            Root<Role> role = roleSubQuery.from(Role.class);
            Expression<Collection<User>> roleUsers = role.get("users");
            roleSubQuery.select(role);
            roleSubQuery.where(cb.equal(role.get("name"), roleName), cb.isMember(root, roleUsers));
            return cb.exists(roleSubQuery);
        };
    }

    @Query(value = FIND_PROJECTS, nativeQuery = true)
    Optional<User> findByEmail(String email);

}
