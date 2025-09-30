package ru.userpetproject.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.userpetproject.dto.user.UserReq;
import ru.userpetproject.service.UserServiceImpl;

@Slf4j
@Component
@RequiredArgsConstructor
@Data
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final UserServiceImpl userService;

    @KafkaListener(topics = "user-in", groupId = "user-pet")
    public void createUser(String message) {
        log.info("Received Message in topic user-in: {}", message);
        try {
            UserReq userReq = objectMapper.readValue(message, UserReq.class);
            userService.create(userReq);
        } catch (JsonProcessingException e) {
            log.error("Can't convert message from kafka to UserReq.class");
            throw new RuntimeException(e.getMessage());
        }
    }
}
