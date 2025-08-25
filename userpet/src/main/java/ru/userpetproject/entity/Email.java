package ru.userpetproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "emails")
@Data
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;
}
