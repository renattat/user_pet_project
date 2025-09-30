package ru.userpetproject.service;

import ru.userpetproject.entity.UserEvent;

public interface KafkaService {

    void sendEventToKafka(Long key, UserEvent userEvent);
}
