package ru.userpetproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.userpetproject.entity.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
