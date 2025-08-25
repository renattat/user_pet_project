package ru.userpetproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
// поменять на lombok               +
// добавить новую сущность Email    +
// добавить List<Email>             +
// доавить entityGraph
// добавить новую сущность Phone    +
// добавить List<Phone>             +
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Email> emails;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Phone> phones;

    public void addEmail(Email email) {
        if(emails == null) {
            emails = new ArrayList<>();
        }
        emails.add(email);
    }

    public void addPhone(Phone phone) {
        if(phones == null) {
            phones = new ArrayList<>();
        }
        phones.add(phone);
    }
}

