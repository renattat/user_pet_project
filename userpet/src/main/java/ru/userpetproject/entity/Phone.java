package ru.userpetproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "phones")
@Data
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String type;
}
