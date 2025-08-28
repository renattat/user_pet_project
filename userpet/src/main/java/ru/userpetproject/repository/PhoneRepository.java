package ru.userpetproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.userpetproject.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
