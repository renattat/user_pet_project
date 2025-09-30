package ru.userpetproject.service;

import ru.userpetproject.entity.User;
import ru.userpetproject.enums.UserStatus;

public interface UserEventService {
    void eventProcessing();

    void saveUserEvent(User userEntity, UserStatus userStatus);
}
