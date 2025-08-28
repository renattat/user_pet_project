package ru.userpetproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
// доавить entityGraph              +
// добавить новую сущность Phone    +
// добавить List<Phone>             +
@NamedEntityGraph(
        name = "User.emails_and_phones",
        attributeNodes = {
                @NamedAttributeNode("emails"),
                @NamedAttributeNode("phones")}
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Email> emails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Phone> phones;

    public void addEmail(Email email) {
        if (emails == null) {
            emails = new ArrayList<>();
        }
        emails.add(email);
        email.setUser(this);
    }

    public void addPhone(Phone phone) {
        if (phones == null) {
            phones = new ArrayList<>();
        }
        phones.add(phone);
        phone.setUser(this);
    }

}

