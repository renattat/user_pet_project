package ru.userpetproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.userpetproject.entity.UserEvent;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
}
