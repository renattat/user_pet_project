package ru.userpetproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.userpetproject.enums.UserStatus;

@Entity
@Data
@Table(name = "user_events")
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Column(name ="event_payload", nullable = false)
    private String eventPayload;

}
