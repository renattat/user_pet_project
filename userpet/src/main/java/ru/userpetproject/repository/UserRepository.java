package ru.userpetproject.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.userpetproject.entity.User;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(value = "User.emails_and_phones", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findById(Long id);
}
