package ru.userpetproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.userpetproject.entity.User;

public interface  UserRepository extends JpaRepository<User, Long> {
}
