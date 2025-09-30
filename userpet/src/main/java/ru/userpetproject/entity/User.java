package ru.userpetproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import ru.userpetproject.enums.UserStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Email> emails = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Phone> phones = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public void setEmails(List<Email> emails) {
        if (emails != null) {
            emails.forEach(this::setEmail);
        }
    }

    public void setPhones(List<Phone> phones) {
        if (phones != null) {
            phones.forEach(this::setPhone);
        }
    }

    public void setEmail(Email email) {
        if (email != null) {
            emails.add(email);
            email.setUser(this);
        }
    }

    public void setPhone(Phone phone) {
        if (phone != null) {
            phones.add(phone);
            phone.setUser(this);
        }
    }
}

